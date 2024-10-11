package com.ae_diary.presentation.ui.screen.main_screen

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
import com.ae_diary.presentation.model.Timetable
import com.ae_diary.presentation.navigation.AppDestination
import com.ae_diary.presentation.navigation.transition.MainScreenTransition
import com.ae_diary.presentation.ui.common.ItemGrid
import com.ae_diary.presentation.ui.screen.main_screen.components.main_screen_ui.CurrentDate
import com.ae_diary.presentation.ui.screen.main_screen.components.main_screen_ui.CurrentSubject
import com.ae_diary.presentation.ui.screen.main_screen.components.main_screen_ui.MainScreenPane
import com.ae_diary.presentation.ui.screen.main_screen.components.main_screen_ui.ProgressBar
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.ae_diary.presentation.util.checkDestinationForAvailability
import com.ae_diary.presentation.viewmodel.MainScreenViewModel
import com.ae_diary.presentation.viewmodel.TimetableViewModel
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

    val mainScreenDestinations = AppDestination.MainScreenDestinations.entries

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
                items = mainScreenDestinations.map { option ->
                    {
                        option.apply {
                            MainScreenPane(
                                topStartIcon = indicationIcon,
                                middleEndIcon = expansionIcon,
                                title = shortTitle,
                                onClick = {
                                    if (checkDestinationForAvailability(
                                            destination = destination,
                                            semesterState = timetableUIState.isSemesterTimeSet,
                                            context = context
                                        )
                                    )
                                        navigator.navigate(
                                            direction = destination,
                                            onlyIfResumed = true
                                        )
                                },
                                onSizeChange = {
                                    mainScreenViewModel.updateSizeReduceState(it)
                                },
                                isReduced = mainScreenUIState.isSizeReducedPane
                            )
                        }
                    }
                }
            )
        }
    }
}