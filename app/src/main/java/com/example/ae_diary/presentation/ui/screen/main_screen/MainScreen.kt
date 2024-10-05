package com.example.ae_diary.presentation.ui.screen.main_screen

import android.widget.Toast
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ae_diary.R
import com.example.ae_diary.presentation.model.Timetable
import com.example.ae_diary.presentation.navigation.transition.MainScreenTransition
import com.example.ae_diary.presentation.ui.common.ItemGrid
import com.example.ae_diary.presentation.ui.screen.destinations.CertificatesScreenDestination
import com.example.ae_diary.presentation.ui.screen.destinations.DiaryScreenDestination
import com.example.ae_diary.presentation.ui.screen.destinations.SettingsScreenDestination
import com.example.ae_diary.presentation.ui.screen.destinations.StatisticsScreenDestination
import com.example.ae_diary.presentation.ui.screen.main_screen.components.main_screen_ui.CurrentDate
import com.example.ae_diary.presentation.ui.screen.main_screen.components.main_screen_ui.CurrentSubject
import com.example.ae_diary.presentation.ui.screen.main_screen.components.main_screen_ui.MainScreenPane
import com.example.ae_diary.presentation.ui.screen.main_screen.components.main_screen_ui.ProgressBar
import com.example.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.example.ae_diary.presentation.viewmodel.MainScreenViewModel
import com.example.ae_diary.presentation.viewmodel.TimetableViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination(style = MainScreenTransition::class)
@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel,
    timetableViewModel: TimetableViewModel,
    navigator: DestinationsNavigator
) {

    val mainScreenUIState by mainScreenViewModel.mainScreenUIState.collectAsStateWithLifecycle()
    val timetableUIState by timetableViewModel.timetableUIState.collectAsStateWithLifecycle()

    val localHeight = LocalView.current.height

    val context = LocalContext.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = DeathNoteTheme.colors.baseBackground
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(
                    shape = DeathNoteTheme.shapes.rounded_bot_strt
                )
                .background(
                    color = DeathNoteTheme.colors.inverseBackground
                )
                .animateContentSize(),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 70.dp,
                    start = 40.dp,
                    end = 40.dp,
                    bottom = 40.dp
                ),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                CurrentDate(mainScreenUIState)
                CurrentSubject(localHeight, mainScreenUIState)

                if (mainScreenUIState.curTimetable != Timetable())
                    ProgressBar(mainScreenUIState)
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DeathNoteTheme.colors.inverseBackground)
                .animateContentSize()
        ) {
            ItemGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = DeathNoteTheme.shapes.rounded_top_end)
                    .background(color = DeathNoteTheme.colors.baseBackground),
                items = listOf(
                    {
                        MainScreenPane(
                            topStartIcon = R.drawable.diary_tl,
                            middleEndIcon = R.drawable.diary_me,
                            title = R.string.diary_bar,
                            onClick = {
                                if (!timetableUIState.isSemesterTimeSet) {
                                    Toast.makeText(context, R.string.semester_time_unset, Toast.LENGTH_SHORT).show()
                                } else
                                    navigator.navigate(DiaryScreenDestination, onlyIfResumed = true)
                            },
                            onSizeChange = {
                                mainScreenViewModel.updateSizeReduceState(
                                    it
                                )
                            },
                            isReduced = mainScreenUIState.isSizeReducedPane
                        )
                    },
                    {
                        MainScreenPane(
                            topStartIcon = R.drawable.list_tl,
                            middleEndIcon = R.drawable.list_me,
                            title = R.string.list_bar,
                            onClick = {
                                if (!timetableUIState.isSemesterTimeSet) {
                                    Toast.makeText(context, R.string.semester_time_unset, Toast.LENGTH_SHORT).show()
                                } else
                                    navigator.navigate(
                                        CertificatesScreenDestination,
                                        onlyIfResumed = true
                                    )
                            },
                            isReduced = mainScreenUIState.isSizeReducedPane
                        )
                    },
                    {
                        MainScreenPane(
                            topStartIcon = R.drawable.stats_tl,
                            middleEndIcon = R.drawable.stats_me,
                            title = R.string.stats_bar,
                            onClick = {
                                navigator.navigate(
                                    StatisticsScreenDestination,
                                    onlyIfResumed = true
                                )
                            },
                            isReduced = mainScreenUIState.isSizeReducedPane
                        )
                    },
                    {
                        MainScreenPane(
                            topStartIcon = R.drawable.settings_tl,
                            middleEndIcon = R.drawable.settings_me,
                            title = R.string.settings_bar,
                            onClick = {
                                navigator.navigate(SettingsScreenDestination, onlyIfResumed = true)
                            },
                            isReduced = mainScreenUIState.isSizeReducedPane
                        )
                    }
                )
            )
        }
    }
}