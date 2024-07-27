package com.example.deathnote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val mainScreenUseCases: MainScreenUseCases
) : ViewModel() {

    private val _allTimetables: MutableStateFlow<List<Timetable>> = MutableStateFlow(emptyList())

    private val _allSubjects: MutableStateFlow<List<Subject>> = MutableStateFlow(emptyList())

    private val _mainScreenUIState: MutableStateFlow<MainScreenUIState> =
        MutableStateFlow(MainScreenUIState())
    val mainScreenUIState = _mainScreenUIState.asStateFlow()

    init {
        viewModelScope.launch {
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
                val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

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

                    val curSubject = _allSubjects.value.firstOrNull { it.id == curTimetable.subjectId }
                        ?: Subject()

                    _mainScreenUIState.value = _mainScreenUIState.value.copy(
                        curTimetable = curTimetable,
                        curSubject = curSubject
                    )
                }
            }

            launch {
                _mainScreenUIState.collectLatest {
                    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

                    val startTimeParsed = LocalTime.parse(it.curTimetable.startTime, timeFormatter).toSecondOfDay()
                    val endTimeParsed = LocalTime.parse(it.curTimetable.endTime, timeFormatter).toSecondOfDay()

                    curTimeFlow.collectLatest { time ->
                        val percentage = (time.toLocalTime().toSecondOfDay() - startTimeParsed).toFloat() / (endTimeParsed - startTimeParsed)
                        delay(1000)

                        _mainScreenUIState.value = _mainScreenUIState.value.copy(
                            percentage = percentage
                        )
                    }
                }
            }
        }
    }
}

private val curTimeFlow = flow {
    while (true) {
        emit(LocalDateTime.now())
        delay(1000)
    }
}
