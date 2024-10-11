package com.ae_diary.presentation.ui.screen.main_screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ae_diary.R
import com.ae_diary.presentation.model.event.TimetableUIEvent
import com.ae_diary.presentation.navigation.AppDestination
import com.ae_diary.presentation.navigation.transition.GeneralTransition
import com.ae_diary.presentation.ui.common.TopBar
import com.ae_diary.presentation.ui.screen.main_screen.components.settings_screen_ui.DatePickerWithDialog
import com.ae_diary.presentation.ui.screen.main_screen.components.settings_screen_ui.SettingsOptionPane
import com.ae_diary.presentation.ui.screen.main_screen.components.settings_screen_ui.SettingsScreenBottomSheet
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.ae_diary.presentation.ui.theme.util.isDarkMode
import com.ae_diary.presentation.ui.theme.util.switchDarkMode
import com.ae_diary.presentation.util.checkDestinationForAvailability
import com.ae_diary.presentation.viewmodel.TimetableViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = GeneralTransition::class)
@Composable
fun SettingsScreen(
    timetableViewModel: TimetableViewModel,
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(
        top = 50.dp,
        start = 25.dp,
        end = 25.dp
    )
) {

    BackHandler {
        navigator.popBackStack()
    }

    val lazyListState = rememberLazyListState()
    val isConstricted by remember { derivedStateOf { lazyListState.firstVisibleItemScrollOffset != 0 } }

    val timetableUIState by timetableViewModel.timetableUIState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val basicSettingsOptions = AppDestination.SettingsTopBarDestinations.entries.toList()

    val backgroundColor by animateColorAsState(targetValue = DeathNoteTheme.colors.baseBackground)

    Column(
        modifier = Modifier
            .background(color = backgroundColor)
            .padding(paddingValues)
            .fillMaxSize()
    ) {

        TopBar(
            destination = AppDestination.MainScreenDestinations.SETTINGS,
            onIconClick = {
                navigator.popBackStack()
            },
            isConstricted = isConstricted
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(top = 30.dp, bottom = 20.dp),
            state = lazyListState
        ) {

            item {
                SettingsOptionPane(
                    icon = R.drawable.moon,
                    isDarkThemePane = true,
                    title = if (isDarkMode()) R.string.light_theme else R.string.dark_theme,
                    description = if (isDarkMode()) R.string.dark_theme_subtitle else R.string.light_theme_subtitle,
                    onClick = { switchDarkMode(context) }
                )
            }

            item {
                SettingsOptionPane(
                    icon = R.drawable.clock,
                    title = if (timetableUIState.isSemesterTimeSet) R.string.delete_semester else R.string.choose_semester_period,
                    description = if (timetableUIState.isSemesterTimeSet) R.string.time_to_say_goodbye else R.string.we_need,
                    onClick = { timetableViewModel.onEvent(TimetableUIEvent.ChangeSettingsScreenBottomSheetState) }
                )
            }

            items(basicSettingsOptions) { option ->
                option.apply {
                    SettingsOptionPane(
                        icon = indicationIcon,
                        title = title,
                        description = description,
                        onClick = {
                            if (checkDestinationForAvailability(
                                    destination = destination,
                                    semesterState = timetableUIState.isSemesterTimeSet,
                                    context = context
                                )
                            )
                                navigator.navigate(direction = destination, onlyIfResumed = true)
                        }
                    )
                }
            }
        }
    }

    SettingsScreenBottomSheet(
        state = timetableUIState,
        onEvent = timetableViewModel::onEvent
    )

    DatePickerWithDialog(
        state = timetableUIState,
        onEvent = timetableViewModel::onEvent
    )
}

