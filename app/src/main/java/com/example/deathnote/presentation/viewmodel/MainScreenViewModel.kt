package com.example.deathnote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deathnote.DiaryApplication.Companion.END_TIME
import com.example.deathnote.DiaryApplication.Companion.START_TIME
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.domain.use_case.main_screen.util.MainScreenUseCases
import com.example.deathnote.presentation.mapper.toPresentation
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.state.MainScreenUIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val mainScreenUseCases: MainScreenUseCases
) : ViewModel() {

    private val _allTimetables: MutableStateFlow<List<Timetable>> = MutableStateFlow(emptyList())

    private val _allSubjects: MutableStateFlow<List<Subject>> = MutableStateFlow(emptyList())

    private val _semesterTime: MutableStateFlow<Pair<LocalDate, LocalDate>> =
        MutableStateFlow(Pair(nowDate, nowDate))

    private val _mainScreenUIState: MutableStateFlow<MainScreenUIState> =
        MutableStateFlow(MainScreenUIState())
    val mainScreenUIState = _mainScreenUIState.asStateFlow()

    private fun findNextTimetable(
        dateTime: LocalDateTime,
        timetable: Timetable
    ): Timetable? {
        val sortedTimetables = _allTimetables.value
            .groupBy { LocalDate.parse(it.date, dateFormatter) }
            .toSortedMap()
            .mapValues { (_, values) ->
                values.sortedBy { LocalTime.parse(it.startTime, timeFormatter) }
            }

        val semesterEnd = _semesterTime.value.second

        var curDate = dateTime.toLocalDate()
        var curDateTimetables = sortedTimetables[curDate] ?: emptyList()

        var curTimetable: Timetable? = if (timetable != Timetable()) {
            curDateTimetables.firstOrNull { it.id == timetable.id }
        } else {
            null
        }

        while (curDate <= semesterEnd) {
            if (curTimetable == null && curDateTimetables.isNotEmpty())
                return curDateTimetables.first()
            else {
                val curIndex = curDateTimetables.indexOf(curTimetable)
                if (curIndex != -1 && curIndex + 1 < curDateTimetables.size) {
                    return curDateTimetables[curIndex + 1]
                }
            }

            curDate = curDate.plusDays(1)
            curDateTimetables = sortedTimetables[curDate] ?: emptyList()
            curTimetable = null
        }

        return null
    }


    init {
        viewModelScope.launch {

            launch(Dispatchers.IO) {
                mainScreenUseCases.GetDataStoreDataUseCase().collectLatest {
                    _semesterTime.value = Pair(
                        first = LocalDate.parse(it[START_TIME], dateFormatter),
                        second = LocalDate.parse(it[END_TIME], dateFormatter)
                    )
                }
            }

            launch {
                curTimeFlow.collectLatest {
                    _mainScreenUIState.value = _mainScreenUIState.value.copy(
                        curTime = it ?: LocalDateTime.now()
                    )
                }
            }

            launch(Dispatchers.IO) {
                mainScreenUseCases.GetAllTimetablesUseCase().collectLatest {
                    _allTimetables.value = it.toPresentation(TimetableDomain::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                mainScreenUseCases.GetAllSubjectsUseCase().collectLatest {
                    _allSubjects.value = it.toPresentation(SubjectDomain::toPresentation)
                }
            }

            launch {
                _mainScreenUIState.collectLatest { state ->
                    val curDate = state.curTime.format(dateFormatter)

                    val curDayTimetables =
                        _allTimetables.value.filter { it.date == curDate }.takeIf {
                            it.isNotEmpty()
                        } ?: emptyList()

                    val curTimetable = curDayTimetables.firstOrNull {
                        val startTime = LocalTime.parse(it.startTime, timeFormatter)
                        val endTime = LocalTime.parse(it.endTime, timeFormatter)

                        val currentTime = state.curTime.toLocalTime()
                        currentTime in startTime..endTime
                    } ?: Timetable()

                    val curSubject =
                        _allSubjects.value.firstOrNull { it.id == curTimetable.subjectId }
                            ?: Subject()

                    _mainScreenUIState.value = _mainScreenUIState.value.copy(
                        curTimetable = curTimetable,
                        curSubject = curSubject
                    )
                }
            }

            launch {
                var startTimeParsed: LocalDateTime = _semesterTime.value.first.atStartOfDay()
                var endTimeParsed: LocalDateTime = _semesterTime.value.second.atStartOfDay()

                _mainScreenUIState.collectLatest {

                    val nextTimetable: Timetable = findNextTimetable(it.curTime, it.curTimetable) ?: Timetable()

                    val curStartTime = LocalDateTime.parse("${it.curTimetable.date}-${it.curTimetable.startTime}", dateTimeFormatter)
                    val curEndTime = LocalDateTime.parse("${it.curTimetable.date}-${it.curTimetable.endTime}", dateTimeFormatter)

                    if (it.curTime in curStartTime..curEndTime) {
                        startTimeParsed = curStartTime
                        endTimeParsed = curEndTime
                    } else if (it.curTime > curEndTime && nextTimetable != Timetable()) {
                        _mainScreenUIState.value = _mainScreenUIState.value.copy(
                            curTimetable = nextTimetable,
                            curSubject = _allSubjects.value.first { subject -> nextTimetable.subjectId == subject.id }
                        )
                    } else if (it.curTime < curStartTime && nextTimetable != Timetable()) {
                        _mainScreenUIState.value = _mainScreenUIState.value.copy(
                            curTimetable = nextTimetable,
                            curSubject = _allSubjects.value.first { subject -> nextTimetable.subjectId == subject.id }
                        )
                    }

                    val percentage =
                        (it.curTime.toEpochSecond(ZoneOffset.UTC) - startTimeParsed.toEpochSecond(ZoneOffset.UTC)).toFloat() / (endTimeParsed.toEpochSecond(ZoneOffset.UTC) - startTimeParsed.toEpochSecond(ZoneOffset.UTC))
                    delay(1000)

                    _mainScreenUIState.value = _mainScreenUIState.value.copy(
                        percentage = percentage
                    )
                }
            }
        }
    }
}

private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy-HH:mm")
private val nowDate = LocalDate.now()

private val curTimeFlow = flow {
    while (true) {
        emit(LocalDateTime.now())
        delay(1000)
    }
}
