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

    private fun findNextTimetable(dateTime: LocalDateTime): Timetable? {
        val sortedTimetables = _allTimetables.value
            .groupBy { LocalDate.parse(it.date, dateFormatter) }
            .toSortedMap()
            .mapValues { (_, values) ->
                values.sortedBy { LocalTime.parse(it.startTime, timeFormatter) }
            }

        val semesterEnd = _semesterTime.value.second
        var currentDate = dateTime.toLocalDate()

        fun getNextTimetable(timetables: List<Timetable>, time: LocalTime): Timetable? {
            return timetables.firstOrNull { LocalTime.parse(it.startTime, timeFormatter) > time }
        }

        val todayTimetables = sortedTimetables[currentDate].orEmpty()
        val nextTodayTimetable = getNextTimetable(todayTimetables, dateTime.toLocalTime())
        if (nextTodayTimetable != null) {
            return nextTodayTimetable
        }

        currentDate = currentDate.plusDays(1)
        while (currentDate <= semesterEnd) {
            val futureTimetables = sortedTimetables[currentDate].orEmpty()
            if (futureTimetables.isNotEmpty()) {
                return futureTimetables.first()
            }
            currentDate = currentDate.plusDays(1)
        }

        return null
    }

    private fun parseDateTime(date: String, time: String): LocalDateTime {
        return LocalDateTime.parse("${date}T$time", dateTimeFormatter)
    }

    private fun updateUIWithNextTimetable(nextTimetable: Timetable) {
        val nextSubject =
            _allSubjects.value.firstOrNull { it.id == nextTimetable.subjectId } ?: Subject()
        _mainScreenUIState.value = _mainScreenUIState.value.copy(
            curTimetable = nextTimetable,
            curSubject = nextSubject
        )
    }

    private fun calculatePercentage(
        currentTime: LocalDateTime,
        startTime: LocalDateTime,
        endTime: LocalDateTime
    ): Float {
        val elapsedTime =
            currentTime.toEpochSecond(ZoneOffset.UTC) - startTime.toEpochSecond(ZoneOffset.UTC)
        val totalTime =
            endTime.toEpochSecond(ZoneOffset.UTC) - startTime.toEpochSecond(ZoneOffset.UTC)
        return if (totalTime > 0) elapsedTime.toFloat() / totalTime else 0f
    }

    private fun findCurrentTimetable(
        timetables: List<Timetable>,
        currentTime: LocalDateTime
    ): Timetable {
        return timetables.firstOrNull {
            val startDate = LocalDate.parse(it.date, dateFormatter)
            val startTime = LocalTime.parse(it.startTime, timeFormatter)
            val endTime = LocalTime.parse(it.endTime, timeFormatter)

            val startDateTime = LocalDateTime.of(startDate, startTime)
            val endDateTime = LocalDateTime.of(startDate, endTime)

            if (endTime.isBefore(startTime)) {
                currentTime.isAfter(startDateTime) || currentTime.isBefore(endDateTime)
            } else {
                currentTime in startDateTime..endDateTime
            }
        } ?: Timetable()
    }

    private fun findPreviousTimetable(currentTime: LocalDateTime): Timetable {
        val sortedTimetables = _allTimetables.value
            .groupBy { LocalDate.parse(it.date, dateFormatter) }
            .toSortedMap()
            .mapValues { (_, values) ->
                values.sortedBy { LocalTime.parse(it.startTime, timeFormatter) }
            }

        val semesterStart = _semesterTime.value.first
        var currentDate = currentTime.toLocalDate()

        fun getPreviousTimetable(timetables: List<Timetable>, time: LocalTime): Timetable? {
            return timetables.lastOrNull { LocalTime.parse(it.startTime, timeFormatter) < time }
        }

        val todayTimetables = sortedTimetables[currentDate].orEmpty()
        val previousTodayTimetable =
            getPreviousTimetable(todayTimetables, currentTime.toLocalTime())
        if (previousTodayTimetable != null) {
            return previousTodayTimetable
        }

        currentDate = currentDate.minusDays(1)
        while (currentDate >= semesterStart) {
            val previousDayTimetables = sortedTimetables[currentDate].orEmpty()
            val previousDayTimetable = getPreviousTimetable(previousDayTimetables, LocalTime.MAX)
            if (previousDayTimetable != null) {
                return previousDayTimetable
            }
            currentDate = currentDate.minusDays(1)
        }

        val semesterStartTimetable = Timetable(
            date = semesterStart.format(dateFormatter),
            startTime = "00:00"
        )
        return semesterStartTimetable
    }

    init {
        viewModelScope.launch {
            launch(Dispatchers.IO) {
                mainScreenUseCases.GetDataStoreDataUseCase().collectLatest { data ->
                    _semesterTime.value =
                        if (data[START_TIME].isNullOrEmpty() || data[END_TIME].isNullOrEmpty())
                            Pair(nowDate, nowDate)
                        else
                            Pair(
                                first = LocalDate.parse(data[START_TIME], dateFormatter),
                                second = LocalDate.parse(data[END_TIME], dateFormatter)
                            )
                }
            }

            launch(Dispatchers.IO) {
                mainScreenUseCases.GetAllTimetablesUseCase().collectLatest { timetables ->
                    _allTimetables.value =
                        timetables.toPresentation(TimetableDomain::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                mainScreenUseCases.GetAllSubjectsUseCase().collectLatest { subjects ->
                    _allSubjects.value = subjects.toPresentation(SubjectDomain::toPresentation)
                }
            }

            launch {
                curTimeFlow.collectLatest { currentTime ->
                    val currentUIState = _mainScreenUIState.value

                    val curDate = currentTime.format(dateFormatter)
                    val curDayTimetables = _allTimetables.value.filter { it.date == curDate }
                    val curTimetable = findCurrentTimetable(curDayTimetables, currentTime)
                    val curSubject =
                        _allSubjects.value.firstOrNull { it.id == curTimetable.subjectId }
                            ?: Subject()

                    val curStartTime = parseDateTime(curTimetable.date, curTimetable.startTime)
                    val curEndTime = parseDateTime(curTimetable.date, curTimetable.endTime)

                    if (curTimetable != Timetable()) {
                        val percentage = calculatePercentage(currentTime, curStartTime, curEndTime)
                        _mainScreenUIState.value = currentUIState.copy(
                            curTime = currentTime,
                            curTimetable = curTimetable,
                            curSubject = curSubject,
                            percentage = percentage,
                            isNextTimetableShown = false
                        )
                    } else {
                        val nextTimetable = findNextTimetable(currentTime) ?: Timetable()

                        if (nextTimetable != Timetable()) {
                            updateUIWithNextTimetable(nextTimetable)
                            val prevTimetable = findPreviousTimetable(
                                parseDateTime(
                                    nextTimetable.date,
                                    nextTimetable.startTime
                                )
                            )
                            val prevEndTime =
                                parseDateTime(prevTimetable.date, prevTimetable.startTime)
                            val nextStartTime =
                                parseDateTime(nextTimetable.date, nextTimetable.startTime)
                            val percentage =
                                calculatePercentage(currentTime, prevEndTime, nextStartTime)
                            _mainScreenUIState.value = currentUIState.copy(
                                curTime = currentTime,
                                curTimetable = nextTimetable,
                                curSubject = _allSubjects.value.firstOrNull { it.id == nextTimetable.subjectId }
                                    ?: Subject(),
                                percentage = percentage,
                                isNextTimetableShown = true
                            )
                        } else {
                            _mainScreenUIState.value = currentUIState.copy(
                                curTime = currentTime,
                                curTimetable = Timetable(),
                                curSubject = Subject(),
                                percentage = 100f,
                                isNextTimetableShown = false
                            )
                        }
                    }
                }
            }
        }
    }
}

private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")
private val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
private val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy'T'HH:mm")
private val nowDate = LocalDate.now()

private val curTimeFlow = flow {
    while (true) {
        emit(LocalDateTime.now())
        delay(1000)
    }
}