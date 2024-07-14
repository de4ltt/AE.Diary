package com.example.deathnote.presentation.ui.screen.main_screen

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
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.DarkTopBar
import com.example.deathnote.presentation.ui.cross_screen_ui.NothingHere
import com.example.deathnote.presentation.ui.screen.main_screen.components.statistics_screen_ui.Stats1_M
import com.example.deathnote.presentation.ui.screen.main_screen.components.statistics_screen_ui.StatsM_1
import com.example.deathnote.presentation.ui.screen.main_screen.components.statistics_screen_ui.StatsM_M
import com.example.deathnote.presentation.ui.screen.main_screen.components.statistics_screen_ui.StudentsDrawer
import com.example.deathnote.presentation.ui.screen.main_screen.components.statistics_screen_ui.SubjectsDrawer
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.viewmodel.StatisticsViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun StatisticsScreen(
    statisticsViewModel: StatisticsViewModel,
    navigator: DestinationsNavigator
) {

    BackHandler {
        navigator.popBackStack()
    }

    val allStudents by statisticsViewModel.allStudents.collectAsStateWithLifecycle()
    val allSubjects by statisticsViewModel.allSubjects.collectAsStateWithLifecycle()
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

        Row(
            modifier = Modifier.padding(horizontal = 25.dp),
            horizontalArrangement = Arrangement.spacedBy(15.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            StudentsDrawer(
                isActive = allStudents.size > 1,
                studentsList = allStudents,
                state = statisticsUIState,
                onEvent = statisticsViewModel::onEvent,
                modifier = Modifier.height(50.dp).weight(1f)
            )
            SubjectsDrawer(
                isActive = allStudents.size > 1,
                subjectList = allSubjects,
                state = statisticsUIState,
                onEvent = statisticsViewModel::onEvent,
                modifier = Modifier.height(50.dp).weight(1f)
            )
        }

        if (allStudents.isNotEmpty() && allSubjects.isNotEmpty())
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 25.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                when (statisticsUIState.mode) {
                    1 -> {
                        items(allSubjects) {
                            Stats1_M(
                                titled = true,
                                subject = Subject(
                                    name = "Mathematica",
                                ),
                                respectfulAbsences = 10,
                                absences = 89,
                                absencePercent = 20
                            )
                        }
                    }

                    2 -> {
                        items(allStudents) {
                            StatsM_1(
                                student = Student(
                                    name = "Vlad",
                                    surname = "Vladichenko"
                                ),
                                respectfulAbsences = 30,
                                absences = 54,
                                absencePercent = 10
                            )
                        }
                    }

                    3 -> {
                        items (allSubjects) {
                            StatsM_M(
                                subject = Subject(
                                    name = "Mathematica",
                                ),
                                respectfulAbsencesPercent = 100,
                                absencesPercent = 100,
                                totAbsencePercent = 100
                            )
                        }
                    }

                }
            }
        else
            NothingHere()
    }

}