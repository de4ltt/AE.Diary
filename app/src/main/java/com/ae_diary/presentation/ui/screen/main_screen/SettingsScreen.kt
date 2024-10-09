package com.ae_diary.presentation.ui.screen.main_screen

import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
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
import com.ae_diary.presentation.ui.screen.destinations.DatabaseSettingsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.LanguageScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.StudentsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.SubjectsScreenDestination
import com.ae_diary.presentation.ui.screen.destinations.TimetableScreenDestination
import com.ae_diary.presentation.ui.screen.main_screen.components.settings_screen_ui.DatePickerWithDialog
import com.ae_diary.presentation.ui.screen.main_screen.components.settings_screen_ui.SettingsOptionPane
import com.ae_diary.presentation.ui.screen.main_screen.components.settings_screen_ui.SettingsScreenBottomSheet
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.ae_diary.presentation.ui.theme.util.isDarkMode
import com.ae_diary.presentation.ui.theme.util.switchDarkMode
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
    val isConstricted by remember {
        derivedStateOf { lazyListState.firstVisibleItemScrollOffset != 0 }
    }

    val timetableUIState by timetableViewModel.timetableUIState.collectAsStateWithLifecycle()

    val context = LocalContext.current

    val backgroundColor by animateColorAsState(targetValue = DeathNoteTheme.colors.baseBackground)

    Column(
        modifier = Modifier
            .background(color = backgroundColor)
            .padding(paddingValues)
            .fillMaxSize()
    ) {

        TopBar(
            destination = AppDestination.SettingsTopBarDestinations.SETTINGS,
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
                    color = DeathNoteTheme.colors.inverseBackground,
                    isDarkThemePane = true,
                    title = if (isDarkMode()) R.string.light_theme else R.string.dark_theme,
                    subtitle = if (isDarkMode()) R.string.dark_theme_subtitle else R.string.light_theme_subtitle,
                    onClick = {
                        switchDarkMode(context)
                    }
                )
            }

            item {
                SettingsOptionPane(
                    icon = R.drawable.students,
                    title = R.string.group_list,
                    subtitle = R.string.to_be_or_not_to_be,
                    onClick = {
                        navigator.navigate(StudentsScreenDestination, onlyIfResumed = true)
                    }
                )
            }

            item {
                SettingsOptionPane(
                    icon = R.drawable.subjects,
                    title = R.string.subjects,
                    subtitle = R.string.the_best_in_the_app,
                    onClick = {
                        navigator.navigate(SubjectsScreenDestination, onlyIfResumed = true)
                    }
                )
            }

            item {
                SettingsOptionPane(
                    icon = R.drawable.timetable,
                    title = R.string.timetable,
                    subtitle = R.string.sorry_for_change,
                    onClick = {
                        if (timetableUIState.isSemesterTimeSet)
                            navigator.navigate(TimetableScreenDestination, onlyIfResumed = true)
                        else Toast.makeText(
                            context,
                            R.string.semester_time_unset,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                )
            }

            item {
                SettingsOptionPane(
                    icon = R.drawable.language,
                    title = R.string.app_language,
                    subtitle = R.string.language_pardon,
                    onClick = {
                        navigator.navigate(LanguageScreenDestination, onlyIfResumed = true)
                    }
                )
            }

            item {
                SettingsOptionPane(
                    icon = R.drawable.clock,
                    title = if (timetableUIState.isSemesterTimeSet) R.string.delete_semester else R.string.choose_semester_period,
                    subtitle = if (timetableUIState.isSemesterTimeSet) R.string.time_to_say_goodbye else R.string.we_need,
                    onClick = {
                        timetableViewModel.onEvent(TimetableUIEvent.ChangeSettingsScreenBottomSheetState)
                    }
                )
            }

            item {
                SettingsOptionPane(
                    icon = R.drawable.data_io,
                    title = R.string.data_io,
                    subtitle = R.string.remember_me,
                    onClick = {
                        navigator.navigate(DatabaseSettingsScreenDestination, onlyIfResumed = true)
                    }
                )
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