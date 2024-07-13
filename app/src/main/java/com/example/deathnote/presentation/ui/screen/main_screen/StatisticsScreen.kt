package com.example.deathnote.presentation.ui.screen.main_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.DarkTopBar
import com.example.deathnote.presentation.ui.cross_screen_ui.NothingHere
import com.example.deathnote.presentation.ui.screen.main_screen.components.statistics_screen_ui.Stats1_M
import com.example.deathnote.presentation.ui.screen.main_screen.components.statistics_screen_ui.StatsM_1
import com.example.deathnote.presentation.ui.screen.main_screen.components.statistics_screen_ui.StatsM_M
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun StatisticsScreen(
    navigator: DestinationsNavigator
) {


    BackHandler {
        navigator.popBackStack()
    }

    Column(
        modifier = Modifier
            .background(color = DeathNoteTheme.colors.baseBackground)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        DarkTopBar(
            destination = AppDestination.MainScreenMenusDestinations.STATISTICS
        )

        if (true)
            LazyColumn(
                contentPadding = PaddingValues(horizontal = 25.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Stats1_M(
                        titled = true,
                        subject = Subject(
                            name = "Mathematical Analysis",
                        ),
                        respectfulAbsences = 10,
                        absences = 89,
                        absencePercent = 20
                    )
                }

                item {
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
                item {
                    StatsM_M(
                        subject = Subject(
                            name = "Mathematical Analysis",
                        ),
                        respectfulAbsencesPercent = 100,
                        absencesPercent = 100,
                        totAbsencePercent = 100
                    )
                }
            }
        else
            NothingHere()
    }

}