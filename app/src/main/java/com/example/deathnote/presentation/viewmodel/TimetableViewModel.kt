package com.example.deathnote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deathnote.DiaryApplication.Companion.END_TIME
import com.example.deathnote.DiaryApplication.Companion.FIRST_WEEK_TYPE
import com.example.deathnote.DiaryApplication.Companion.START_TIME
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
import com.example.deathnote.presentation.util.toDayOfWeek
import com.example.deathnote.presentation.util.toWeekType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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

    private val _semesterTime: MutableStateFlow<Triple<String, String, WeekType>> =
        MutableStateFlow(Triple(nowTime, nowTime, WeekType.ODD))

    fun onEvent(event: TimetableUIEvent) = when (event) {
        is TimetableUIEvent.ChangeBottomSheetDayOfWeek ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    bottomSheetDayOfWeek = event.dayOfWeek
                )
            }

        is TimetableUIEvent.ChangeBottomSheetEndTime ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    bottomSheetEndTime = event.endTime
                )
            }

        is TimetableUIEvent.ChangeBottomSheetStartTime ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
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

        TimetableUIEvent.ChangeSettingsScreenBottomSheetState ->
            viewModelScope.launch {
                _timetableUIState.value = _timetableUIState.value.copy(
                    settingsBottomSheetState = !_timetableUIState.value.settingsBottomSheetState
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
                timetableUIStateValue.settingsBottomSheetFirstWeekType
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
                _timetableUIState.value = _timetableUIState.value.copy(
                    settingsBottomSheetStartDate = event.time
                )
            }

        TimetableUIEvent.IdleSemesterTime ->
            setSemesterTime(nowTime, nowTime, WeekType.ODD)
    }

    private fun setSemesterTime(start: String, end: String, firstWeekType: WeekType) =
        viewModelScope.launch(Dispatchers.IO) {
            timetableUseCases.SetSemesterTimeUseCase(start, end, firstWeekType.weekType)
        }

    private fun upsertTimetable() = viewModelScope.launch(Dispatchers.IO) {

        val timetableUIStateValue = _timetableUIState.value

        findFirstMatchDay(
            timetableUIStateValue.bottomSheetDayOfWeek,
            timetableUIStateValue.bottomSheetWeekType
        )?.let {

            var curDate =
                it
            val endDate =
                LocalDate.parse(
                    _semesterTime.value.second,
                    DateTimeFormatter.ofPattern("dd.MM.yyyy")
                )
            val pattern =
                DateTimeFormatter.ofPattern("dd.MM.yyyy")


            val timetable = Timetable(
                date = it.format(pattern),
                subjectId = timetableUIStateValue.bottomSheetSubject.id,
                startTime = timetableUIStateValue.bottomSheetStartTime,
                endTime = timetableUIStateValue.bottomSheetEndTime,
                weekType = timetableUIStateValue.bottomSheetWeekType.weekType
            )

            viewModelScope.launch(Dispatchers.IO) {
                while (curDate <= endDate) {
                    if (
                        curDate.dayOfWeek.value == LocalDate.parse(
                            timetable.date,
                            pattern
                        ).dayOfWeek.value
                    )
                        timetableUseCases.UpsertTimetableUseCase(
                            timetable.copy(
                                date = curDate.format(pattern)
                            ).toDomain()
                        )
                    curDate = curDate.plusDays(14)
                }
            }
        }
    }

    private fun deleteTimetable(timetable: Timetable) = viewModelScope.launch(Dispatchers.IO) {

        var curDate =
            LocalDate.parse(
                timetable.date,
                DateTimeFormatter.ofPattern("dd.MM.yyyy")
            )
        val endDate =
            LocalDate.parse(
                _semesterTime.value.second,
                DateTimeFormatter.ofPattern("dd.MM.yyyy")
            )
        val pattern =
            DateTimeFormatter.ofPattern("dd.MM.yyyy")

        while (curDate <= endDate) {
            timetableUseCases.DeleteTimetableUseCase(
                timetable.copy(
                    date = curDate.format(pattern)
                ).toDomain()
            )
            curDate = curDate.plusDays(14)
        }
    }

    private fun findFirstMatchDay(dayOfWeek: DayOfWeek, weekType: WeekType): LocalDate? {
        val semesterTimeValue = _semesterTime.value
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        var curDate = LocalDate.parse(semesterTimeValue.first, formatter)
        val endDate = LocalDate.parse(semesterTimeValue.second, formatter)
        var curWeekType = semesterTimeValue.third

        while (
            curDate.dayOfWeek.value != dayOfWeek.code ||
            curWeekType != weekType
        ) {
            if (curDate > endDate) return null
            curDate = curDate.plusDays(1)
            if (curDate.dayOfWeek.value == 1)
                curWeekType = if (curWeekType == WeekType.ODD) WeekType.EVEN else WeekType.ODD
        }

        return curDate
    }

    init {
        viewModelScope.launch {

            launch(Dispatchers.IO) {
                timetableUseCases.GetDataStoreDataUseCase().collectLatest {
                    it[FIRST_WEEK_TYPE]?.let { weekType ->
                        _semesterTime.value = Triple(
                            it[START_TIME] ?: nowTime,
                            it[END_TIME] ?: nowTime,
                            WeekType.entries.first { weekTypeEntry ->
                                weekTypeEntry.weekType == weekType
                            }
                        )
                    }

                }
            }

            launch(Dispatchers.IO) {
                timetableUseCases.GetAllTimetablesUseCase().collectLatest {
                    val timetables: List<Timetable> =
                        it.toPresentation(TimetableDomain::toPresentation)

                    val pattern = DateTimeFormatter.ofPattern("dd.MM.yyyy")

                    _allTimetables.value = timetables.groupBy { timetable ->
                        Pair(
                            first = LocalDate.parse(
                                timetable.date,
                                pattern
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

        }
    }
}

private val nowTime = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))