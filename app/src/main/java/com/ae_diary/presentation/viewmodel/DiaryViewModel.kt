package com.ae_diary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ae_diary.domain.model.AbsenceDomain
import com.ae_diary.domain.model.SubjectDomain
import com.ae_diary.domain.model.TimetableDomain
import com.ae_diary.domain.use_case.diary.util.DiaryUseCases
import com.ae_diary.presentation.mapper.toDomain
import com.ae_diary.presentation.mapper.toPresentation
import com.ae_diary.presentation.model.Absence
import com.ae_diary.presentation.model.Subject
import com.ae_diary.presentation.model.Timetable
import com.ae_diary.presentation.model.event.DiaryUIEvent
import com.ae_diary.presentation.model.state.DiaryUIState
import com.ae_diary.presentation.util.TimeFormatter.dateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
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

    fun isItHoliday(date: String) =
        _allTimetables.value.any { it.date == date } && LocalDate.parse(date, dateFormatter) <= LocalDate.now()

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
        val nextSubject =
            if (curSubjectIndex == _allDaySubjects.value.lastIndex) _allDaySubjects.value.first()
            else _allDaySubjects.value[curSubjectIndex + 1]

        val dayTimetablesBySubjectId =
            _allDayTimetables.value.groupBy { it.subjectId }.mapValues { it.value[0] }

        dayTimetablesBySubjectId[nextSubject.id]?.let {
            _diaryUIState.value = _diaryUIState.value.copy(
                curSubject = nextSubject,
                curTimetable = it
            )
        }
    }

    private fun setPrevSubject() {
        val curSubjectIndex = _allDaySubjects.value.indexOf(_diaryUIState.value.curSubject)
        val prevSubject = if (curSubjectIndex == 0) _allDaySubjects.value.last()
        else _allDaySubjects.value[curSubjectIndex - 1]

        val dayTimetablesBySubjectId =
            _allDayTimetables.value.groupBy { it.subjectId }.mapValues { it.value[0] }

        dayTimetablesBySubjectId[prevSubject.id]?.let {
            _diaryUIState.value = _diaryUIState.value.copy(
                curSubject = prevSubject,
                curTimetable = it
            )
        }
    }

    init {
        viewModelScope.launch {

            launch(Dispatchers.IO) {
                diaryUseCases.GetAllAbsenceUseCase().collectLatest { absences ->
                    _allAbsence.value = absences.toPresentation(AbsenceDomain::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                diaryUseCases.GetAllSubjectsUseCase().collectLatest {
                    _allSubjects.value = it.toPresentation(SubjectDomain::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                diaryUseCases.GetAllTimetablesUseCase().collectLatest { timetables ->
                    _diaryUIState.collectLatest { state ->

                        val receivedTimetables: List<Timetable> =
                            timetables.toPresentation(TimetableDomain::toPresentation)

                        _allTimetables.value = receivedTimetables

                        _allDayTimetables.value = receivedTimetables.filter { timetable ->
                            timetable.date == state.curDate
                        }
                    }
                }
            }

            launch {
                _allSubjects.collectLatest { subjects ->
                    _allDayTimetables.collectLatest { dayTimetables ->
                        val filteredSubjects = subjects.filter { subject ->
                            dayTimetables.any { timetable -> timetable.subjectId == subject.id }
                        }

                        _allDaySubjects.value = filteredSubjects

                        if (filteredSubjects.isNotEmpty()) {
                            _diaryUIState.value = _diaryUIState.value.copy(
                                curSubject = filteredSubjects.first(),
                                curTimetable = dayTimetables.first { it.subjectId == filteredSubjects.first().id }
                            )
                        }
                    }
                }
            }

        }
    }
}
