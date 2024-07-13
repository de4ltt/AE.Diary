package com.example.deathnote.presentation.viewmodel

import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.util.copy
import com.example.deathnote.domain.model.AbsenceDomain
import com.example.deathnote.domain.model.HolidayDomain
import com.example.deathnote.domain.model.SubjectDismissedDomain
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.model.WeekTypeDomain
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
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class DiaryViewModel @Inject constructor(
    private val diaryUseCases: DiaryUseCases,
    private val subjectUseCases: SubjectUseCases,
    private val timetableUseCases: TimetableUseCases
) : ViewModel() {

    private val allSubjects = MutableStateFlow<List<Subject>>(emptyList())

    private val allDaySubjects = MutableStateFlow<List<Subject>>(emptyList())

    private val timetablesByDay = MutableStateFlow<List<Int>>(emptyList())

    private val allHolidays = MutableStateFlow<List<Holiday>>(emptyList())

    private val _allDaySubjectsDismissed = MutableStateFlow<List<SubjectDismissed>>(emptyList())
    val allDaySubjectsDismissed = _allDaySubjectsDismissed.asStateFlow()

    private val _allDayAbsence = MutableStateFlow<List<Absence>>(emptyList())
    val allDayAbsence = _allDayAbsence.asStateFlow()

    private val _diaryUIState = MutableStateFlow<DiaryUIState>(DiaryUIState())
    val diaryUIState = _diaryUIState.asStateFlow()

    private val _allWeekTypes = MutableStateFlow<List<WeekType>>(emptyList())
    val allWeekTypes = _allWeekTypes.asStateFlow()

    fun onEvent(event: DiaryUIEvent) = when (event) {

        is DiaryUIEvent.SetDay -> setDay(event.day)

        is DiaryUIEvent.SetDaySubjectStudentPresent -> {
            deleteStudentAbsence(
                Absence(
                    studentId = event.student.id,
                    subjectId = event.daySubject.id,
                    date = event.date,
                    respectful = false
                )
            )
        }

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

        is DiaryUIEvent.SetDaySubjectStudentAbsentRespectful -> {
            addRespectfulAbsence(
                Absence(
                    studentId = event.student.id,
                    subjectId = diaryUIState.value.curSubject.id,
                    date = diaryUIState.value.date,
                    respectful = true
                )
            )
        }

        is DiaryUIEvent.SetDaySubjectStudentPresentRespectful -> {
            deleteStudentAbsence(
                Absence(
                    studentId = event.student.id,
                    subjectId = diaryUIState.value.curSubject.id,
                    date = diaryUIState.value.date,
                    respectful = true
                )
            )
        }

        is DiaryUIEvent.SetDaySubjectDismissed -> {
            addDismissedSubject(
                SubjectDismissed(
                    day = diaryUIState.value.date,
                    subjectId = diaryUIState.value.curSubject.id
                )
            )
        }

        DiaryUIEvent.ChangeSettingsScreenBottomSheetState -> {
            viewModelScope.launch(Dispatchers.IO) {
                _diaryUIState.value = _diaryUIState.value.copy(
                    isSettingsBottomSheetOpen = !_diaryUIState.value.isSettingsBottomSheetOpen
                )
            }
        }

        is DiaryUIEvent.ChangeEndOfSemester -> {
            viewModelScope.launch(Dispatchers.IO) {
                _diaryUIState.value = _diaryUIState.value.copy(
                    endOfSemester = event.end
                )
            }
        }

        DiaryUIEvent.ChangeFirstWeekType -> {
            viewModelScope.launch(Dispatchers.IO) {
                _diaryUIState.value = _diaryUIState.value.copy(
                    firstWeekType = if (diaryUIState.value.firstWeekType == "O") "E" else "O"
                )
            }
        }

        is DiaryUIEvent.ChangeStartOfSemester -> {
            viewModelScope.launch(Dispatchers.IO) {
                _diaryUIState.value = _diaryUIState.value.copy(
                    startOfSemester = event.start
                )
            }
        }

        DiaryUIEvent.ChangeSetSemesterTime -> {
            viewModelScope.launch(Dispatchers.IO) {
                _diaryUIState.value = _diaryUIState.value.copy(
                    isTimeSet = !diaryUIState.value.isTimeSet
                )
            }
        }

        DiaryUIEvent.DeleteSemester -> {
            deleteAllWeekType()
            deleteHoliday()
            viewModelScope.launch(Dispatchers.IO) {
                _diaryUIState.value = DiaryUIState()
            }
        }

        DiaryUIEvent.AddSemesterTime -> {
            val pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy")
            val semStart = LocalDate.parse(
                diaryUIState.value.startOfSemester,
                pattern
            )
            val semEnd = LocalDate.parse(
                diaryUIState.value.endOfSemester,
                pattern
            )

            var curWeekType = diaryUIState.value.firstWeekType
            var curDate = semStart

            viewModelScope.launch(Dispatchers.IO) {
                while (curDate <= semEnd) {
                    if (diaryUIState.value.holidays.contains(curDate.dayOfWeek.value))
                        addHoliday(curDate.format(pattern))
                    if (curDate.dayOfWeek.value == 1)
                        curWeekType = if (curWeekType == "O") "E" else "O"
                    addWeekType(
                        WeekType(
                            type = curWeekType,
                            day = curDate.format(pattern)
                        )
                    )

                    curDate = curDate.plusDays(1)
                }
            }
        }

        is DiaryUIEvent.AddHoliday -> {
            viewModelScope.launch(Dispatchers.IO) {
                _diaryUIState.value = _diaryUIState.value.copy(
                    holidays = _diaryUIState.value.holidays + event.day
                )
            }
        }

        is DiaryUIEvent.DeleteHoliday -> {
            viewModelScope.launch(Dispatchers.IO) {
                _diaryUIState.value = _diaryUIState.value.copy(
                    holidays = _diaryUIState.value.holidays - event.day
                )
            }
        }

        DiaryUIEvent.RefreshSubject -> {
            TODO()
        }

        DiaryUIEvent.SetNextDaySubject -> {
            val curIndex = allDaySubjects.value.indexOf(diaryUIState.value.curSubject)

            val nextSubjectId =
                if (curIndex == allDaySubjects.value.size - 1)
                    0
                else curIndex + 1

            viewModelScope.launch {
                withContext(Dispatchers.Main) {
                    _diaryUIState.value = _diaryUIState.value.copy(
                        curSubject = allDaySubjects.value[nextSubjectId]
                    )
                }
            }
        }

        DiaryUIEvent.SetPreviousDaySubject -> {
            val curIndex = allDaySubjects.value.indexOf(diaryUIState.value.curSubject)

            val prevSubjectId =
                if (curIndex == 0)
                    allDaySubjects.value.size - 1
                else curIndex - 1

            viewModelScope.launch {
                withContext(Dispatchers.Main) {
                    _diaryUIState.value = _diaryUIState.value.copy(
                        curSubject = allDaySubjects.value[prevSubjectId]
                    )
                }
            }
        }

        is DiaryUIEvent.ChangeDate ->
            setDay(date = event.date)

        DiaryUIEvent.ChangeDatePickerState ->
            viewModelScope.launch(Dispatchers.IO) {
                _diaryUIState.value = _diaryUIState.value.copy(
                    isDatePickerOpen = !_diaryUIState.value.isDatePickerOpen
                )
            }
    }

    private fun getWeekType(day: String) =
        if (allWeekTypes.value.any { it.day == day })
            _allWeekTypes.value.first { it.day == day }.type
        else "O"

    fun isItWorkDay(day: String) =
        allWeekTypes.value.any {
            it.day == day
        } && !allHolidays.value.contains(Holiday(day))

    private fun setDay(date: String) =
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                _diaryUIState.value = _diaryUIState.value.copy(
                    date = date
                )
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

    private fun addWeekType(weekType: WeekType) =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.AddWeekTypeUseCase(weekType.toDomain())
        }

    private fun deleteAllWeekType() =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.DeleteAllWeekTypeUseCase()
        }

    private fun addHoliday(date: String) =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.AddHolidayUseCase(Holiday(date).toDomain())
        }

    private fun deleteHoliday() =
        viewModelScope.launch(Dispatchers.IO) {
            diaryUseCases.DeleteHolidayUseCase()
        }

    init {
        viewModelScope.launch {

            launch(Dispatchers.IO) {
                subjectUseCases.GetAllSubjectsUseCase().collectLatest {
                    allSubjects.value = it.toPresentation(SubjectDomain::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                diaryUseCases.GetAllHolidaysUseCase().collectLatest {
                    allHolidays.value = it.toPresentation(HolidayDomain::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                diaryUseCases.GetWeekTypesUseCase().collectLatest {
                    _allWeekTypes.value = it.toPresentation(WeekTypeDomain::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                _diaryUIState.collectLatest { diaryState ->
                    diaryUseCases.GetAllDayAbsenceUseCase(diaryState.date).collectLatest {
                        _allDayAbsence.value = it.toPresentation(AbsenceDomain::toPresentation)
                    }
                }
            }

            launch {
                _diaryUIState.collectLatest { diaryState ->
                    withContext(Dispatchers.IO) {
                        timetableUseCases.GetTimetablesByDayUseCase(
                            diaryState.date.toTimetableDate(
                                getWeekType(diaryState.date)
                            )
                        )
                            .collectLatest { timetable ->
                                timetablesByDay.value = timetable
                            }
                    }
                }
            }

            launch {
                timetablesByDay.collectLatest { timetables ->
                    allDaySubjects.value = allSubjects.value.filter { subject ->
                        timetables.contains(subject.id)
                    }
                }
            }

            launch {
                allDaySubjects.collectLatest {
                    _diaryUIState.value = _diaryUIState.value.copy(
                        curSubject = if (it.isEmpty()) Subject() else it.first()
                    )
                }
            }

            launch {
                _allWeekTypes.collectLatest { weekType ->
                    _diaryUIState.value = _diaryUIState.value.copy(
                        isTimeSet = weekType.isNotEmpty()
                    )
                }
            }
        }
    }
}