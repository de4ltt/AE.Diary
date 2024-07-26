package com.example.deathnote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deathnote.domain.model.AbsenceDomain
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.domain.use_case.diary.util.DiaryUseCases
import com.example.deathnote.presentation.mapper.toDomain
import com.example.deathnote.presentation.mapper.toPresentation
import com.example.deathnote.presentation.model.Absence
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.model.state.DiaryUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryUseCases: DiaryUseCases
) : ViewModel() {

    private val _diaryUIState: MutableStateFlow<DiaryUIState> = MutableStateFlow(DiaryUIState())
    val diaryUIState = _diaryUIState.asStateFlow()

    private val _allAbsence: MutableStateFlow<List<Absence>> = MutableStateFlow(emptyList())
    val allAbsence = _allAbsence.asStateFlow()

    private val _allDayTimetables: MutableStateFlow<List<Timetable>> = MutableStateFlow(emptyList())
    val allDayTimetables = _allDayTimetables.asStateFlow()

    private val _allSubjects: MutableStateFlow<List<Subject>> = MutableStateFlow(emptyList())

    private val _allTimetables: MutableStateFlow<List<Timetable>> = MutableStateFlow(emptyList())

    private val _allDaySubjects: MutableStateFlow<List<Subject>> = MutableStateFlow(emptyList())

    fun onEvent(event: DiaryUIEvent) = when (event) {
        is DiaryUIEvent.AddStudentAbsence -> addAbsence(event.absence)
        is DiaryUIEvent.ChangeDate -> changeDate(event.date)
        is DiaryUIEvent.DeleteStudentAbsence -> deleteAbsence(event.absence)
        is DiaryUIEvent.DismissSubject -> dismissCurrentSubject()
        is DiaryUIEvent.UndismissSubject -> undismissCurrentSubject()
        DiaryUIEvent.SetNextSubject -> setNextSubject()
        DiaryUIEvent.SetPrevSubject -> setPrevSubject()
        DiaryUIEvent.ChangeDatePickerState -> toggleDatePickerState()
    }

    fun isItHoliday(date: String) = _allTimetables.value.any { it.date == date }

    private fun changeDate(date: String) {
        _diaryUIState.value = _diaryUIState.value.copy(curDate = date)
    }

    private fun toggleDatePickerState() {
        _diaryUIState.value = _diaryUIState.value.copy(
            isDatePickerOpen = !_diaryUIState.value.isDatePickerOpen
        )
    }

    private fun addAbsence(absence: Absence) {
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.AddStudentAbsenceUseCase(absence.toDomain())
        }
    }

    private fun deleteAbsence(absence: Absence) {
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.DeleteStudentAbsenceUseCase(absence.toDomain())
        }
    }

    private fun dismissCurrentSubject() {
        val currentTimetable = _allDayTimetables.value.firstOrNull {
            it.subjectId == _diaryUIState.value.curSubject.id
        }
        currentTimetable?.let { dismissSubject(it) }
    }

    private fun undismissCurrentSubject() {
        val currentTimetable = _allDayTimetables.value.firstOrNull {
            it.subjectId == _diaryUIState.value.curSubject.id
        }
        currentTimetable?.let { undismissSubject(it) }
    }

    private fun dismissSubject(timetable: Timetable) = viewModelScope.launch(Dispatchers.IO) {
        diaryUseCases.DismissSubjectUseCase(timetable.toDomain())
    }

    private fun undismissSubject(timetable: Timetable) = viewModelScope.launch(Dispatchers.IO) {
        diaryUseCases.UndismissSubjectUseCase(timetable.toDomain())
    }

    private fun setNextSubject() {
        val curSubjectIndex = _allDaySubjects.value.indexOf(_diaryUIState.value.curSubject)
        _diaryUIState.value = _diaryUIState.value.copy(
            curSubject = if (curSubjectIndex == _allDaySubjects.value.lastIndex) _allDaySubjects.value.first()
            else _allDaySubjects.value[curSubjectIndex + 1]
        )
    }

    private fun setPrevSubject() {
        val curSubjectIndex = _allDaySubjects.value.indexOf(_diaryUIState.value.curSubject)
        _diaryUIState.value = _diaryUIState.value.copy(
            curSubject = if (curSubjectIndex == 0) _allDaySubjects.value.last()
            else _allDaySubjects.value[curSubjectIndex - 1]
        )
    }

    init {
        viewModelScope.launch {

            launch(Dispatchers.IO) {
                diaryUseCases.GetAllAbsenceUseCase().collect { absences ->
                    _allAbsence.value = absences.toPresentation(AbsenceDomain::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                diaryUseCases.GetAllSubjectsUseCase().collect {
                    _allSubjects.value = it.toPresentation(SubjectDomain::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                diaryUseCases.GetAllTimetablesUseCase().collect { timetables ->
                    val receivedTimetables: List<Timetable> =
                        timetables.toPresentation(TimetableDomain::toPresentation)

                    _allTimetables.value = receivedTimetables

                    _allDayTimetables.value = receivedTimetables.filter { timetable ->
                        timetable.date == _diaryUIState.value.curDate
                    }
                }
            }

            launch {
                combine(_allDayTimetables, _allSubjects) { dayTimetables, subjects ->
                    subjects.filter { subject ->
                        dayTimetables.any { timetable -> timetable.subjectId == subject.id }
                    }
                }.collect { filteredSubjects ->
                    _allDaySubjects.value = filteredSubjects
                    if (filteredSubjects.isNotEmpty()) {
                        _diaryUIState.value = _diaryUIState.value.copy(
                            curSubject = filteredSubjects.first()
                        )
                    }
                }
            }

        }
    }
}
