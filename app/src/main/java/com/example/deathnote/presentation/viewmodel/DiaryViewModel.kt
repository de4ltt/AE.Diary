package com.example.deathnote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.deathnote.domain.model.AbsenceDomain
import com.example.deathnote.domain.model.HolidayDomain
import com.example.deathnote.domain.model.SubjectDismissedDomain
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.use_case.diary.util.DiaryUseCases
import com.example.deathnote.domain.use_case.subject.util.SubjectUseCases
import com.example.deathnote.domain.use_case.timetable.util.TimetableUseCases
import com.example.deathnote.presentation.mapper.toDomain
import com.example.deathnote.presentation.mapper.toPresentation
import com.example.deathnote.presentation.model.Absence
import com.example.deathnote.presentation.model.Holiday
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.SubjectDismissed
import com.example.deathnote.presentation.model.WeekType
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.model.state.DiaryUIState
import com.example.deathnote.presentation.util.toTimetableDate
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryUseCases: DiaryUseCases,
    private val subjectUseCases: SubjectUseCases,
    private val timetableUseCases: TimetableUseCases
) : ViewModel() {

    private val _allDayAbsence = MutableStateFlow<List<Absence>>(emptyList())
    val allDayAbsence = _allDayAbsence.asStateFlow()

    private val _allDaySubjects = MutableStateFlow<List<Subject>>(emptyList())
    val allDaySubjects = _allDaySubjects.asStateFlow()

    private val _allDaySubjectsDismissed = MutableStateFlow<List<SubjectDismissed>>(emptyList())
    val allDaySubjectsDismissed = _allDaySubjectsDismissed.asStateFlow()

    private val timetablesByDay = MutableStateFlow<List<Int>>(emptyList())

    private val _allHolidays = MutableStateFlow<List<Holiday>>(emptyList())

    private val _diaryUIState = MutableStateFlow<DiaryUIState>(DiaryUIState())
    val diaryUIState = _diaryUIState.asStateFlow()

    fun onEvent(event: DiaryUIEvent) = when (event) {

        is DiaryUIEvent.SetDay -> setDay(event.day)

        is DiaryUIEvent.SetDaySubjectStudentPresent ->
            deleteStudentAbsence(
                Absence(
                    studentId = event.student.id,
                    subjectId = event.daySubject.id,
                    date = event.date,
                    respectful = false
                )
            )

        is DiaryUIEvent.UnsetDaySubjectDismissed -> {
            deleteDismissedSubject(
                SubjectDismissed(
                    day = diaryUIState.value.date,
                    subjectId = diaryUIState.value.curSubject.id
                )
            )
        }

        is DiaryUIEvent.SetDaySubjectStudentAbsent -> {
            addStudentAbsence(
                Absence(
                    studentId = event.student.id,
                    subjectId = diaryUIState.value.curSubject.id,
                    date = diaryUIState.value.date,
                    respectful = false
                )
            )
        }

        is DiaryUIEvent.SetDaySubjectStudentAbsentRespectful ->
            addRespectfulAbsence(
                Absence(
                    studentId = event.student.id,
                    subjectId = diaryUIState.value.curSubject.id,
                    date = diaryUIState.value.date,
                    respectful = true
                )
            )

        is DiaryUIEvent.SetDaySubjectStudentPresentRespectful ->
            deleteStudentAbsence(
                Absence(
                    studentId = event.student.id,
                    subjectId = diaryUIState.value.curSubject.id,
                    date = diaryUIState.value.date,
                    respectful = true
                )
            )

        is DiaryUIEvent.SetDaySubjectDismissed -> {
            addDismissedSubject(
                SubjectDismissed(
                    day = diaryUIState.value.date,
                    subjectId = diaryUIState.value.curSubject.id
                )
            )
        }

        is DiaryUIEvent.SetNextDaySubject ->
            viewModelScope.launch(Dispatchers.IO) {
                val nextSubject = allDaySubjects.value[
                    (allDaySubjects.value.indexOf(diaryUIState.value.curSubject) + 1)
                            % allDaySubjects.value.size
                ]

                _diaryUIState.value = _diaryUIState.value.copy(
                    curSubject = nextSubject
                )
            }

        is DiaryUIEvent.SetPreviousDaySubject -> {
            val prevSubject = if (allDaySubjects.value.indexOf(diaryUIState.value.curSubject) == 0)
                allDaySubjects.value.last()
            else
                allDaySubjects.value[
                    allDaySubjects.value.indexOf(diaryUIState.value.curSubject) - 1
                ]

            viewModelScope.launch(Dispatchers.IO) {
                _diaryUIState.value = _diaryUIState.value.copy(
                    curSubject = prevSubject
                )
            }
        }
    }

    fun getWeekType(day: String) =
        "O"

    fun isItHoliday(day: String) =
        _allHolidays.value.any { it.date == day }

    private fun setDay(date: String) =
        viewModelScope.launch(Dispatchers.IO) {
            _diaryUIState.value = _diaryUIState.value.copy(
                date = date
            )
        }

    private fun addDismissedSubject(subjectDismissed: SubjectDismissed) =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.AddDismissedSubjectUseCase(subjectDismissed.toDomain())
        }

    private fun addStudentAbsence(absence: Absence) =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.AddStudentAbsenceUseCase(absence.toDomain())
        }

    private fun addRespectfulAbsence(absence: Absence) =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.AddStudentRespectfulAbsenceUseCase(absence.toDomain())
        }

    private fun deleteStudentAbsence(absence: Absence) =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.DeleteStudentAbsenceUseCase(absence.toDomain())
        }

    private fun deleteDismissedSubject(subjectDismissed: SubjectDismissed) =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.DeleteDismissedSubjectUseCase(subjectDismissed.toDomain())
        }

    fun addWeekType(weekType: WeekType) =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.AddWeekTypeUseCase(weekType.toDomain())
        }

    fun deleteAllWeekType() =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.DeleteAllWeekTypeUseCase()
        }

    fun addHoliday(date: String) =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.AddHolidayUseCase(Holiday(date).toDomain())
        }

    fun deleteHoliday(holiday: Holiday) =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.DeleteHolidayUseCase(holiday.toDomain())
        }

    init {
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.GetAllHolidaysUseCase().collect {
                withContext(Dispatchers.Main) {
                    _allHolidays.value = it.toPresentation(HolidayDomain::toPresentation)
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                timetableUseCases.GetTimetablesByDayUseCase(
                    diaryUIState.value.date.toTimetableDate(getWeekType(diaryUIState.value.date))
                ).collect { timetable ->
                    timetablesByDay.value = timetable
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            subjectUseCases.GetAllSubjectsUseCase().collect { subjects ->
                withContext(Dispatchers.Main) {
                    _allDaySubjects.value = subjects.toPresentation(SubjectDomain::toPresentation)
                }.also {
                    _allDaySubjects.value =
                        _allDaySubjects.value.filter {
                            timetablesByDay.asStateFlow().value.contains(
                                it.id
                            )
                        }
                }.also {
                    if (_allDaySubjects.value.isNotEmpty())
                        _diaryUIState.value = _diaryUIState.value.copy(
                            curSubject = _allDaySubjects.value[0]
                        )
                }
            }
        }


        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                diaryUseCases.GetAllDayAbsenceUseCase(diaryUIState.value.date).collect {
                    _allDayAbsence.value = it.toPresentation(AbsenceDomain::toPresentation)
                }
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            withContext(Dispatchers.Main) {
                diaryUseCases.GetAllDaySubjectsDismissed(diaryUIState.value.date).collect {
                    _allDaySubjectsDismissed.value =
                        it.toPresentation(SubjectDismissedDomain::toPresentation)
                }
            }
        }
    }
}