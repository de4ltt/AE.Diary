package com.example.deathnote.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deathnote.DiaryApplication.Companion.END_TIME
import com.example.deathnote.DiaryApplication.Companion.FIRST_WEEK_TYPE
import com.example.deathnote.DiaryApplication.Companion.HOLIDAYS
import com.example.deathnote.DiaryApplication.Companion.START_TIME
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.domain.use_case.timetable.util.TimetableUseCases
import com.example.deathnote.presentation.mapper.toDomain
import com.example.deathnote.presentation.mapper.toPresentation
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.event.TimetableUIEvent
import com.example.deathnote.presentation.model.state.TimetableUIState
import com.example.deathnote.presentation.model.util.DayOfWeek
import com.example.deathnote.presentation.model.util.WeekType
import com.example.deathnote.presentation.util.TimeFormatter.dateFormatter
import com.example.deathnote.presentation.util.TimeFormatter.nowDate
import com.example.deathnote.presentation.util.TimeFormatter.nowDateFormatted
import com.example.deathnote.presentation.util.toDayOfWeek
import com.example.deathnote.presentation.util.toWeekType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalTime
import javax.inject.Inject

@HiltViewModel
class TimetableViewModel @Inject constructor(
    private val timetableUseCases: TimetableUseCases
) : ViewModel() {

    private val _timetableUIState: MutableStateFlow<TimetableUIState> =
        MutableStateFlow(TimetableUIState())
    val timetableUIState = _timetableUIState.asStateFlow()

    private val _allTimetables: MutableStateFlow<Map<Pair<DayOfWeek, WeekType>, List<Timetable>>> =
        MutableStateFlow(emptyMap())
    val allTimetables = _allTimetables.asStateFlow()

    private val _availableSubjects: MutableStateFlow<List<Subject>> = MutableStateFlow(emptyList())
    val availableSubjects = _availableSubjects.asStateFlow()

    private val _semesterTime: MutableStateFlow<Triple<String, String, WeekType>> =
        MutableStateFlow(
            Triple(
                nowDateFormatted,
                nowDate.plusDays(14).format(dateFormatter),
                WeekType.ODD
            )
        )
    val semesterTime = _semesterTime.asStateFlow()

    private val _daysOfWeek: MutableStateFlow<List<Int>> = MutableStateFlow(emptyList())
    val daysOfWeek = _daysOfWeek.asStateFlow()

    private val _holidays: MutableStateFlow<List<DayOfWeek>> = MutableStateFlow(emptyList())

    fun onEvent(event: TimetableUIEvent) = when (event) {
        is TimetableUIEvent.ChangeBottomSheetDayOfWeek ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    bottomSheetDayOfWeek = event.dayOfWeek
                )
            }

        is TimetableUIEvent.ChangeBottomSheetEndTime ->
            viewModelScope.launch {
                val timetableUIStateValue = _timetableUIState.value

                if (LocalTime.parse(event.endTime) < LocalTime.parse(timetableUIStateValue.bottomSheetStartTime))
                    _timetableUIState.value = timetableUIStateValue.copy(
                        bottomSheetStartTime = event.endTime,
                        bottomSheetEndTime = event.endTime
                    )
                else
                    _timetableUIState.value = timetableUIStateValue.copy(
                        bottomSheetEndTime = event.endTime
                    )
            }

        is TimetableUIEvent.ChangeBottomSheetStartTime ->
            viewModelScope.launch {
                val timetableUIStateValue = _timetableUIState.value

                if (
                    LocalTime.parse(event.startTime) > LocalTime.parse(timetableUIStateValue.bottomSheetEndTime)
                )
                    _timetableUIState.value = timetableUIStateValue.copy(
                        bottomSheetStartTime = event.startTime,
                        bottomSheetEndTime = event.startTime
                    )
                else
                    _timetableUIState.value = timetableUIStateValue.copy(
                        bottomSheetStartTime = event.startTime
                    )
            }

        TimetableUIEvent.ChangeBottomSheetState ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    bottomSheetState = !_timetableUIState.value.bottomSheetState
                )
            }

        is TimetableUIEvent.ChangeBottomSheetSubject ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    bottomSheetSubject = event.subject
                )
            }

        TimetableUIEvent.ChangeBottomSheetSubjectPickerState ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    bottomSheetSubjectPickerState = !_timetableUIState.value.bottomSheetSubjectPickerState
                )
            }

        TimetableUIEvent.ChangeBottomSheetTimePickerState ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    bottomSheetTimePickerState = !_timetableUIState.value.bottomSheetTimePickerState
                )
            }

        TimetableUIEvent.ChangeBottomSheetWeekType ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    bottomSheetWeekType = if (_timetableUIState.value.bottomSheetWeekType == WeekType.ODD) WeekType.EVEN else WeekType.ODD
                )
            }

        TimetableUIEvent.ChangeCurWeekType ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    curWeekType = if (_timetableUIState.value.curWeekType == WeekType.ODD) WeekType.EVEN else WeekType.ODD
                )
            }

        is TimetableUIEvent.ChangeSettingsScreenBottomSheetDatePickerState ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    settingBottomSheetDatePickerState = event.state
                )
            }

        is TimetableUIEvent.DeleteTimetable ->
            deleteTimetable(event.timetable)

        TimetableUIEvent.UpsertTimetable ->
            upsertTimetable()

        is TimetableUIEvent.IdleBottomSheet ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    bottomSheetSubject = Subject(),
                    bottomSheetDayOfWeek = event.dayOfWeek,
                    bottomSheetWeekType = _timetableUIState.value.curWeekType,
                    bottomSheetStartTime = "08:00",
                    bottomSheetEndTime = "09:20"
                )
            }

        TimetableUIEvent.ChangeSemesterTime -> {
            val timetableUIStateValue = _timetableUIState.value
            setSemesterTime(
                timetableUIStateValue.settingsBottomSheetStartDate,
                timetableUIStateValue.settingsBottomSheetEndDate,
                timetableUIStateValue.settingsBottomSheetFirstWeekType,
                timetableUIStateValue.settingsBottomSheetHolidays
            )
        }

        is TimetableUIEvent.ChangeBottomSheetStartTimePicker ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    bottomSheetTimePickerStartPick =
                    if (_timetableUIState.value.bottomSheetTimePickerStartPick == "start") "end" else "start"
                )
            }

        is TimetableUIEvent.SettingsBottomSheetAddHoliday ->
            viewModelScope.launch(Dispatchers.IO) {
                _timetableUIState.value = _timetableUIState.value.copy(
                    settingsBottomSheetHolidays = _timetableUIState.value.settingsBottomSheetHolidays + event.dayOfWeek
                )
            }

        is TimetableUIEvent.SettingsBottomSheetDeleteHoliday ->
            viewModelScope.launch(Dispatchers.IO) {
                _timetableUIState.value = _timetableUIState.value.copy(
                    settingsBottomSheetHolidays = _timetableUIState.value.settingsBottomSheetHolidays - event.dayOfWeek
                )
            }

        TimetableUIEvent.SettingsBottomSheetChangeFirstWeekType ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    settingsBottomSheetFirstWeekType = if (_timetableUIState.value.settingsBottomSheetFirstWeekType == WeekType.ODD) WeekType.EVEN else WeekType.ODD
                )
            }

        is TimetableUIEvent.SettingsBottomSheetChangeSemesterEndTime ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    settingsBottomSheetEndDate = event.time
                )
            }

        is TimetableUIEvent.SettingsBottomSheetChangeSemesterStartTime ->
            viewModelScope.launch {
                val timetableUIStateValue = _timetableUIState.value

                if (
                    LocalDate.parse(
                        timetableUIStateValue.settingsBottomSheetEndDate,
                        dateFormatter
                    ) < LocalDate.parse(event.time, dateFormatter).plusDays(14)
                )
                    _timetableUIState.value = timetableUIStateValue.copy(
                        settingsBottomSheetEndDate = LocalDate.parse(event.time, dateFormatter)
                            .plusDays(14).format(
                            dateFormatter
                        ),
                        settingsBottomSheetStartDate = event.time
                    )
                else
                    _timetableUIState.value = timetableUIStateValue.copy(
                        settingsBottomSheetStartDate = event.time
                    )
            }

        TimetableUIEvent.IdleSemesterTime -> {
            setSemesterTime(nowDateFormatted, nowDateFormatted, WeekType.ODD, emptyList())
            deleteSemester()
        }

        TimetableUIEvent.ChangeSettingsScreenBottomSheetState ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    settingsBottomSheetState = !_timetableUIState.value.settingsBottomSheetState
                )
            }

        is TimetableUIEvent.ChangeCurPage ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    curPage = event.page
                )
            }
    }

    private fun deleteSemester() = viewModelScope.launch(Dispatchers.IO) {
        timetableUseCases.DeleteAllTimetablesUseCase()
    }

    private fun setSemesterTime(
        start: String,
        end: String,
        firstWeekType: WeekType,
        holidays: List<DayOfWeek>
    ) =
        viewModelScope.launch(Dispatchers.IO) {
            timetableUseCases.SetSemesterTimeUseCase(
                start,
                end,
                firstWeekType.weekType,
                holidays.map { it.code }.joinToString(",")
            )
        }

    private fun upsertTimetable() = viewModelScope.launch(Dispatchers.IO) {
        val timetableUIStateValue = _timetableUIState.value
        findFirstMatchDay(
            timetableUIStateValue.bottomSheetDayOfWeek,
            timetableUIStateValue.bottomSheetWeekType
        )?.let {
            var curDate = it
            val endDate = LocalDate.parse(_semesterTime.value.second, dateFormatter)
            val timetable = Timetable(
                date = it.format(dateFormatter),
                subjectId = timetableUIStateValue.bottomSheetSubject.id,
                startTime = timetableUIStateValue.bottomSheetStartTime,
                endTime = timetableUIStateValue.bottomSheetEndTime,
                weekType = timetableUIStateValue.bottomSheetWeekType.weekType
            )

            while (curDate <= endDate) {
                if (curDate.dayOfWeek == LocalDate.parse(timetable.date, dateFormatter).dayOfWeek) {
                    timetableUseCases.UpsertTimetableUseCase(
                        timetable.copy(date = curDate.format(dateFormatter)).toDomain()
                    )
                }
                curDate = curDate.plusDays(14)
            }
        }
    }

    private fun deleteTimetable(timetable: Timetable) = viewModelScope.launch(Dispatchers.IO) {
        var curDate = LocalDate.parse(timetable.date, dateFormatter)
        val endDate = LocalDate.parse(_semesterTime.value.second, dateFormatter)
        while (curDate <= endDate) {
            timetableUseCases.DeleteTimetableUseCase(
                timetable.copy(date = curDate.format(dateFormatter)).toDomain()
            )
            curDate = curDate.plusDays(14)
        }
    }

    private fun findFirstMatchDay(dayOfWeek: DayOfWeek, weekType: WeekType): LocalDate? {
        val semesterTimeValue = _semesterTime.value
        var curDate = LocalDate.parse(semesterTimeValue.first, dateFormatter)
        val endDate = LocalDate.parse(semesterTimeValue.second, dateFormatter)
        var curWeekType = semesterTimeValue.third

        while (curDate.dayOfWeek.value != dayOfWeek.code || curWeekType != weekType) {
            if (curDate > endDate) return null
            curDate = curDate.plusDays(1)
            if (curDate.dayOfWeek.value == DayOfWeek.MONDAY.code) {
                curWeekType = if (curWeekType == WeekType.ODD) WeekType.EVEN else WeekType.ODD
            }
        }
        return curDate
    }

    init {
        viewModelScope.launch {

            launch {
                _timetableUIState.collectLatest { state ->
                    _daysOfWeek.value = DayOfWeek.entries.filter {
                        !state.settingsBottomSheetHolidays.contains(
                            it
                        )
                    }.map { it.code }
                }
            }

            launch(Dispatchers.IO) {
                _timetableUIState.collectLatest { state ->
                    val daysOfWeek = _daysOfWeek.first()
                    timetableUseCases.GetAllSubjectsUseCase().collectLatest { subjects ->
                        val collectedSubjects: List<Subject> = subjects.toPresentation(SubjectDomain::toPresentation)
                        val filteredSubjects = collectedSubjects.filter { subject ->
                            val dayOfWeek = daysOfWeek[state.curPage].toDayOfWeek()
                            val weekType = state.curWeekType
                            val listTimetables = _allTimetables.value[Pair(dayOfWeek, weekType)]
                            listTimetables?.none { it.subjectId == subject.id } ?: true
                        }
                        _availableSubjects.value = filteredSubjects
                    }
                }
            }


            launch(Dispatchers.IO) {
                timetableUseCases.GetDataStoreDataUseCase().collectLatest {
                    it[FIRST_WEEK_TYPE]?.let { weekType ->
                        _semesterTime.value = Triple(
                            it[START_TIME] ?: nowDateFormatted,
                            it[END_TIME] ?: nowDate.plusDays(14).format(dateFormatter),
                            WeekType.entries.first { weekTypeEntry -> weekTypeEntry.weekType == weekType }
                        )
                    }

                    it[HOLIDAYS]?.let { holidays ->
                        _holidays.value = holidays.split(",").mapNotNull { dayOfWeek ->
                            if (dayOfWeek.isNotEmpty())
                                DayOfWeek.entries.firstOrNull { dayOfWeekEntry -> dayOfWeekEntry.code == dayOfWeek.toInt() }
                            else null
                        }
                    }

                    Log.d("holidays", "${_holidays.value}")
                }
            }

            launch(Dispatchers.IO) {
                timetableUseCases.GetAllTimetablesUseCase().collectLatest {
                    val timetables: List<Timetable> =
                        it.toPresentation(TimetableDomain::toPresentation)
                    _allTimetables.value = timetables.groupBy { timetable ->
                        Pair(
                            first = LocalDate.parse(
                                timetable.date,
                                dateFormatter
                            ).dayOfWeek.value.toDayOfWeek(),
                            second = timetable.weekType.toWeekType()
                        )
                    }.mapValues { list ->
                        list.value.groupBy { entry -> entry.date }.values.first()
                    }
                }
            }

            launch {
                _semesterTime.collectLatest {
                    _timetableUIState.value = _timetableUIState.value.copy(
                        isSemesterTimeSet = it.first != it.second
                    )
                }
            }

            launch {
                _holidays.collectLatest {
                    _timetableUIState.value = _timetableUIState.value.copy(
                        settingsBottomSheetHolidays = it
                    )
                }
            }
        }
    }
}
