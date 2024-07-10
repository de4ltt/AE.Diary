package com.example.deathnote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.SubjectDismissed
import com.example.deathnote.presentation.model.WeekType
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.model.state.DiaryUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
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
                    date = event.date
                )
            )

        is DiaryUIEvent.UnsetDaySubjectDismissed ->
            deleteDismissedSubject(
                SubjectDismissed(
                    day = diaryUIState.value.date,
                    subjectId = diaryUIState.value.curSubject.id
                )
            )

        is DiaryUIEvent.SetDaySubjectStudentAbsent ->
            addStudentAbsence(
                Absence(
                    studentId = event.student.id,
                    subjectId = diaryUIState.value.curSubject.id,
                    date = diaryUIState.value.date
                )
            )

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
                    date = diaryUIState.value.date
                )
            )

        is DiaryUIEvent.SetDaySubjectDismissed ->
            addDismissedSubject(
                SubjectDismissed(
                    day = diaryUIState.value.date,
                    subjectId = diaryUIState.value.curSubject.id
                )
            )

        is DiaryUIEvent.SetNextDaySubject ->
            viewModelScope.launch(Dispatchers.IO) {
                _diaryUIState.value = _diaryUIState.value.copy(
                    curSubject = event.daySubject
                )
            }

        is DiaryUIEvent.SetPreviousDaySubject ->
            viewModelScope.launch(Dispatchers.IO) {
                _diaryUIState.value = _diaryUIState.value.copy(
                    curSubject = event.daySubject
                )
            }
    }

    fun getWeekType(day: String) =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.GetWeekTypeByDayUseCase(day).collect {

            }
        }

    fun isSubjectDismissed(subject: Subject) =
        _allDaySubjectsDismissed.value.any { it.subjectId == subject.id }

    fun isItHoliday(day: String) =
        _allHolidays.value.any { it.date == day }

    private fun setDay(date: String) =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.GetAllDayAbsenceUseCase(date).collect {
                _allDayAbsence.value = it.toPresentation(AbsenceDomain::toPresentation)
            }

            diaryUseCases.GetAllDaySubjectsDismissed(date).collect {
                _allDaySubjectsDismissed.value =
                    it.toPresentation(SubjectDismissedDomain::toPresentation)
            }
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
                _allHolidays.value = it.toPresentation(HolidayDomain::toPresentation)
            }

            subjectUseCases.GetAllSubjectsUseCase().collect {
                _allDaySubjects.value = it.toPresentation(SubjectDomain::toPresentation)
            }

            _allDaySubjects.value = _allDaySubjects.value.filter {
                timetableUseCases.GetTimetablesByDayUseCase()
            }
        }
    }
}