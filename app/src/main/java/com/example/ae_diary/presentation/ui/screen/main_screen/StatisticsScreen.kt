package com.example.ae_diary.presentation.ui.screen.main_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ae_diary.presentation.model.interfaces.StatisticsMode
import com.example.ae_diary.presentation.navigation.AppDestination
import com.example.ae_diary.presentation.navigation.transition.GeneralTransition
import com.example.ae_diary.presentation.ui.common.NothingHere
import com.example.ae_diary.presentation.ui.common.top_bar.DarkTopBar
import com.example.ae_diary.presentation.ui.screen.main_screen.components.statistics_screen_ui.Stats1_M
import com.example.ae_diary.presentation.ui.screen.main_screen.components.statistics_screen_ui.StatsM_1
import com.example.ae_diary.presentation.ui.screen.main_screen.components.statistics_screen_ui.StatsM_M
import com.example.ae_diary.presentation.ui.screen.main_screen.components.statistics_screen_ui.StudentDrawerExtension
import com.example.ae_diary.presentation.ui.screen.main_screen.components.statistics_screen_ui.StudentsDrawer
import com.example.ae_diary.presentation.ui.screen.main_screen.components.statistics_screen_ui.SubjectDrawerExtension
import com.example.ae_diary.presentation.ui.screen.main_screen.components.statistics_screen_ui.SubjectsDrawer
import com.example.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.example.ae_diary.presentation.viewmodel.StatisticsViewModel
import com.example.ae_diary.presentation.viewmodel.StudentViewModel
import com.example.ae_diary.presentation.viewmodel.SubjectViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = GeneralTransition::class)
@Composable
fun StatisticsScreen(
    studentViewModel: StudentViewModel,
    subjectViewModel: SubjectViewModel,
    statisticsViewModel: StatisticsViewModel,
    navigator: DestinationsNavigator
) {

    BackHandler {
        navigator.popBackStack()
    }

    val allStudents by studentViewModel.allStudents.collectAsStateWithLifecycle()
    val allSubjects by subjectViewModel.allSubjects.collectAsStateWithLifecycle()

    val allStatistic1M by statisticsViewModel.allStatistic1M.collectAsStateWithLifecycle()
    val allStatisticM1 by statisticsViewModel.allStatisticM1.collectAsStateWithLifecycle()
    val allStatisticMM by statisticsViewModel.allStatisticMM.collectAsStateWithLifecycle()

    val statisticsUIState by statisticsViewModel.statisticsUIState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .background(color = DeathNoteTheme.colors.baseBackground)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(25.dp)
    ) {

        DarkTopBar(
            destination = AppDestination.MainScreenMenusDestinations.STATISTICS
        )

        NothingHere(
            modifier = Modifier.weight(1f),
            targetState = allStatisticMM.isEmpty()
        ) {

            Column(
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {

                Row(
                    modifier = Modifier.padding(horizontal = 25.dp),
                    horizontalArrangement = Arrangement.spacedBy(15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    StudentsDrawer(
                        isActive = allStudents.size > 1,
                        state = statisticsUIState,
                        onEvent = statisticsViewModel::onEvent,
                        modifier = Modifier
                            .height(50.dp)
                            .weight(1f)
                    )
                    SubjectsDrawer(
                        isActive = allSubjects.size > 1,
                        state = statisticsUIState,
                        onEvent = statisticsViewModel::onEvent,
                        modifier = Modifier
                            .height(50.dp)
                            .weight(1f)
                    )
                }


                LazyColumn(
                    contentPadding = PaddingValues(
                        start = 25.dp,
                        end = 25.dp,
                        bottom = 25.dp
                    ),
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    when (statisticsUIState.mode) {
                        StatisticsMode.OneStudentManySubjects -> {
                            items(allStatistic1M) {
                                Stats1_M(
                                    titled = allStatistic1M.first() == it,
                                    subject = allSubjects.first { subject -> subject.id == it.subjectId },
                                    respectfulAbsences = it.resAbsence,
                                    absences = it.absence,
                                    absencePercent = it.absencePercent
                                )
                            }
                        }

                        StatisticsMode.ManyStudentsOneSubject -> {
                            items(allStatisticM1) {
                                StatsM_1(
                                    titled = allStatisticM1.first() == it,
                                    student = allStudents.first { student -> student.id == it.studentId },
                                    respectfulAbsences = it.resAbsence,
                                    absences = it.absence,
                                    absencePercent = it.absencePercent
                                )
                            }
                        }

                        StatisticsMode.AllStudentsAllSubjects -> {
                            items(allStatisticMM) {
                                StatsM_M(
                                    titled = allStatisticMM.first() == it,
                                    subject = allSubjects.first { subject -> subject.id == it.subjectId },
                                    respectfulAbsencesPercent = it.resAbsencePercent,
                                    presencePercent = it.presencePercent,
                                    totAbsencePercent = it.absencePercent
                                )
                            }
                        }

                    }
                }
            }
        }
    }

    StudentDrawerExtension(
        state = statisticsUIState,
        onEvent = statisticsViewModel::onEvent,
        studentsList = allStudents
    )

    SubjectDrawerExtension(
        state = statisticsUIState,
        onEvent = statisticsViewModel::onEvent,
        subjectList = allSubjects
    )
}