package com.example.deathnote.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.deathnote.domain.model.AbsenceDomain
import com.example.deathnote.domain.model.StudentDomain
import com.example.deathnote.domain.model.SubjectDomain
import com.example.deathnote.domain.model.TimetableDomain
import com.example.deathnote.domain.use_case.statistic.util.StatisticUseCases
import com.example.deathnote.presentation.mapper.toPresentation
import com.example.deathnote.presentation.model.Absence
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.event.StatisticsUIEvent
import com.example.deathnote.presentation.model.state.StatisticsUIState
import com.example.deathnote.presentation.model.util.Statistic1M
import com.example.deathnote.presentation.model.util.StatisticM1
import com.example.deathnote.presentation.model.util.StatisticMM
import com.example.deathnote.presentation.model.util.StatisticsMode
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
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
            val curMode = statisticsUIState.value.mode

            if (
                curMode == StatisticsMode.OneStudentManySubjects && event.mode == StatisticsMode.ManyStudentsOneSubject ||
                curMode == StatisticsMode.ManyStudentsOneSubject && event.mode == StatisticsMode.OneStudentManySubjects
            )
                viewModelScope.launch {
                    _statisticsUIState.value = _statisticsUIState.value.copy(
                        mode = StatisticsMode.AllStudentsAllSubjects
                    )
                }
            else
                viewModelScope.launch {
                    _statisticsUIState.value = _statisticsUIState.value.copy(
                        mode = event.mode
                    )
                }
        }
    }

    private fun getPastTimetables(timetables: List<Timetable>): Int {
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        return timetables.filter { LocalDate.parse(it.date, formatter) < LocalDate.now() }.size
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