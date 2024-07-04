package com.example.deathnote.presentation.ui.screen.main_screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.SettingsTopBar
import com.example.deathnote.presentation.ui.screen.main_screen.settings_screen_ui.SettingsOptionPane
import com.example.deathnote.presentation.ui.screen.settings.destinations.LanguageScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.StudentsScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.SubjectsScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.TimetableScreenDestination
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.ui.theme.util.isDarkMode
import com.example.deathnote.presentation.ui.theme.util.switchDarkMode
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SettingsScreen(
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

    val context = LocalContext.current

    val backgroundColor by animateColorAsState(targetValue = DeathNoteTheme.colors.baseBackground)

    Column(
        modifier = Modifier
            .background(color = backgroundColor)
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        SettingsTopBar(
            destination = AppDestination.SettingsTopBarDestinations.SETTINGS,
            onIconClick = {
                navigator.popBackStack()
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            contentPadding = PaddingValues(bottom = 10.dp)
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
                        navigator.navigate(TimetableScreenDestination, onlyIfResumed = true)
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
        }
    }
}