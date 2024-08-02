package com.example.ae_diary.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ae_diary.domain.model.AbsenceDomain
import com.example.ae_diary.domain.model.StudentDomain
import com.example.ae_diary.domain.model.SubjectDomain
import com.example.ae_diary.domain.model.TimetableDomain
import com.example.ae_diary.domain.use_case.statistic.util.StatisticUseCases
import com.example.ae_diary.presentation.mapper.toPresentation
import com.example.ae_diary.presentation.model.Absence
import com.example.ae_diary.presentation.model.Student
import com.example.ae_diary.presentation.model.Subject
import com.example.ae_diary.presentation.model.Timetable
import com.example.ae_diary.presentation.model.event.StatisticsUIEvent
import com.example.ae_diary.presentation.model.interfaces.StatisticsMode
import com.example.ae_diary.presentation.model.state.StatisticsUIState
import com.example.ae_diary.presentation.model.util.Statistic1M
import com.example.ae_diary.presentation.model.util.StatisticM1
import com.example.ae_diary.presentation.model.util.StatisticMM
import com.example.ae_diary.presentation.util.TimeFormatter.dateFormatter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import kotlin.math.roundToInt

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    private val statisticUseCases: StatisticUseCases
) : ViewModel() {

    private val _allSubjects: MutableStateFlow<List<Subject>> = MutableStateFlow(emptyList())

    private val _allStudents: MutableStateFlow<List<Student>> = MutableStateFlow(emptyList())

    private val _allAbsence: MutableStateFlow<List<Absence>> = MutableStateFlow(emptyList())

    private val _allTimetables: MutableStateFlow<List<Timetable>> = MutableStateFlow(emptyList())

    private val _allStatistic1M: MutableStateFlow<List<Statistic1M>> = MutableStateFlow(emptyList())
    val allStatistic1M = _allStatistic1M.asStateFlow()

    private val _allStatisticM1: MutableStateFlow<List<StatisticM1>> = MutableStateFlow(emptyList())
    val allStatisticM1 = _allStatisticM1.asStateFlow()

    private val _allStatisticMM: MutableStateFlow<List<StatisticMM>> = MutableStateFlow(emptyList())
    val allStatisticMM = _allStatisticMM.asStateFlow()

    private val _statisticsUIState: MutableStateFlow<StatisticsUIState> =
        MutableStateFlow(StatisticsUIState())
    val statisticsUIState = _statisticsUIState.asStateFlow()

    fun onEvent(event: StatisticsUIEvent) = when (event) {
        is StatisticsUIEvent.ChangeStudent ->
            viewModelScope.launch {
                _statisticsUIState.value = _statisticsUIState.value.copy(
                    curStudent = event.student
                )
            }

        StatisticsUIEvent.ChangeStudentDrawerState ->
            viewModelScope.launch {
                _statisticsUIState.value = _statisticsUIState.value.copy(
                    isStudentDrawerOpen = !_statisticsUIState.value.isStudentDrawerOpen
                )
            }

        is StatisticsUIEvent.ChangeSubject ->
            viewModelScope.launch {
                _statisticsUIState.value = _statisticsUIState.value.copy(
                    curSubject = event.subject
                )
            }

        StatisticsUIEvent.ChangeSubjectDrawerState ->
            viewModelScope.launch {
                _statisticsUIState.value = _statisticsUIState.value.copy(
                    isSubjectDrawerOpen = !_statisticsUIState.value.isSubjectDrawerOpen
                )
            }

        is StatisticsUIEvent.ChangeMode -> {
            val curState = statisticsUIState.value


            when (curState.mode) {
                StatisticsMode.AllStudentsAllSubjects -> viewModelScope.launch {
                        _statisticsUIState.value = _statisticsUIState.value.copy(
                            mode = event.mode
                        )
                    }
                StatisticsMode.ManyStudentsOneSubject -> {
                    if (event.mode == StatisticsMode.OneStudentManySubjects && curState.isStudentDrawerOpen)
                        viewModelScope.launch {
                            _statisticsUIState.value = _statisticsUIState.value.copy(
                                mode = event.mode
                            )
                        }

                    if (event.mode == StatisticsMode.OneStudentManySubjects && curState.isSubjectDrawerOpen)
                        viewModelScope.launch {
                            _statisticsUIState.value = _statisticsUIState.value.copy(
                                mode = StatisticsMode.AllStudentsAllSubjects
                            )
                        }

                    else viewModelScope.launch {
                        _statisticsUIState.value = _statisticsUIState.value.copy(
                            mode = event.mode
                        )
                    }
                }
                StatisticsMode.OneStudentManySubjects -> {
                    if (event.mode == StatisticsMode.ManyStudentsOneSubject && curState.isSubjectDrawerOpen)
                        viewModelScope.launch {
                            _statisticsUIState.value = _statisticsUIState.value.copy(
                                mode = event.mode
                            )
                        }

                    if (event.mode == StatisticsMode.ManyStudentsOneSubject && curState.isStudentDrawerOpen)
                        viewModelScope.launch {
                            _statisticsUIState.value = _statisticsUIState.value.copy(
                                mode = StatisticsMode.AllStudentsAllSubjects
                            )
                        }
                    else viewModelScope.launch {
                        _statisticsUIState.value = _statisticsUIState.value.copy(
                            mode = event.mode
                        )
                    }
                }
            }
        }
    }

    private fun getPastTimetables(timetables: List<Timetable>): Int {
        return timetables.filter { LocalDate.parse(it.date, dateFormatter) <= LocalDate.now() }.size
    }


    init {
        viewModelScope.launch {

            launch(Dispatchers.IO) {
                statisticUseCases.GetAllAbsenceUseCase().collectLatest { absences ->
                    _allAbsence.value = absences.toPresentation(AbsenceDomain::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                statisticUseCases.GetAllTimetablesUseCase().collectLatest { timetables ->
                    _allTimetables.value =
                        timetables.toPresentation(TimetableDomain::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                statisticUseCases.GetAllSubjectsUseCase().collectLatest { subjects ->
                    _allSubjects.value = subjects.toPresentation(SubjectDomain::toPresentation)
                }
            }

            launch(Dispatchers.IO) {
                statisticUseCases.GetAllStudentsUseCase().collectLatest { students ->
                    _allStudents.value = students.toPresentation(StudentDomain::toPresentation)
                }
            }

            launch {
                _statisticsUIState.collectLatest { state ->
                    _allStudents.collectLatest { students ->
                        _allSubjects.collectLatest { subjects ->
                            if (state.curStudent !in students && students.isNotEmpty())
                                _statisticsUIState.value = state.copy(
                                    curStudent = students.first()
                                )

                            if (state.curSubject !in subjects && subjects.isNotEmpty())
                                _statisticsUIState.value = state.copy(
                                    curSubject = subjects.first()
                                )
                        }
                    }
                }
            }

            launch {
                _allTimetables.collectLatest { timetables ->
                    val timetablesSize: Float = getPastTimetables(timetables).toFloat()

                    val allTimetablesBySubjectId = timetables
                        .groupBy { it.subjectId }
                        .mapValues { timetable -> timetable.value.map { it.id } }

                    _allAbsence.collectLatest { absences ->
                        _statisticsUIState.collectLatest { state ->
                            _allStudents.collectLatest { students ->
                                _allSubjects.collectLatest { subjects ->
                                    _allStatistic1M.value = subjects.map { subject ->
                                        val studentId = state.curStudent.id

                                        Statistic1M(
                                            studentId = studentId,
                                            subjectId = subject.id,
                                            absence = absences.count { it.studentId == studentId },
                                            resAbsence = absences.count { it.studentId == studentId && it.respectful },
                                            absencePercent = if (timetablesSize > 0)
                                                (100 * (absences.count { it.studentId == studentId && !it.respectful } / timetablesSize)).roundToInt()
                                            else 0
                                        )
                                    }

                                    _allStatisticM1.value = students.map { student ->
                                        val subjectId = state.curSubject.id

                                        val subjectTimetablesIds = allTimetablesBySubjectId[subjectId] ?: emptyList()

                                        StatisticM1(
                                            subjectId = subjectId,
                                            studentId = student.id,
                                            absence = absences.count { absence ->
                                                student.id == absence.studentId && subjectTimetablesIds.contains(absence.timetableId)
                                            },
                                            resAbsence = absences.count { absence ->
                                                student.id == absence.studentId && subjectTimetablesIds.contains(absence.timetableId) && absence.respectful
                                            },
                                            absencePercent = if (timetablesSize > 0)
                                                (100 * (absences.count { it.studentId == student.id && !it.respectful && subjectTimetablesIds.contains(it.timetableId) } / timetablesSize)).roundToInt()
                                            else 0
                                        )
                                    }

                                    _allStatisticMM.value = subjects.map { subject ->
                                        val subjectTimetablesIds = allTimetablesBySubjectId[subject.id] ?: emptyList()

                                        StatisticMM(
                                            subjectId = subject.id,
                                            presencePercent = if (timetablesSize > 0)
                                                (100 - 100 * (absences.count { subjectTimetablesIds.contains(it.timetableId) } / (timetablesSize * students.size))).roundToInt()
                                            else 100,
                                            resAbsencePercent = if (timetablesSize > 0)
                                                (100 * (absences.count { subjectTimetablesIds.contains(it.timetableId) && it.respectful } / (timetablesSize * students.size))).roundToInt()
                                            else 0,
                                            absencePercent = if (timetablesSize > 0)
                                                (100 * (absences.count { subjectTimetablesIds.contains(it.timetableId) && !it.respectful } / (timetablesSize * students.size))).roundToInt()
                                            else 0
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}