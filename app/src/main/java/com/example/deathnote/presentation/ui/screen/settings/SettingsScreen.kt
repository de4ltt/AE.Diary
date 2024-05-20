package com.example.deathnote.presentation.ui.screen.settings

import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.SettingsTopBar
import com.example.deathnote.presentation.ui.screen.settings.composable.settings_screen_ui.SettingsOptionPane
import com.example.deathnote.presentation.ui.screen.settings.destinations.LanguageScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.StudentsScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.StyleScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.SubjectsScreenDestination
import com.example.deathnote.presentation.ui.screen.settings.destinations.TimetableScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.annotation.RootNavGraph
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@RootNavGraph(start = true)
@Destination
@Composable
fun SettingsScreen(
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(
        vertical = 50.dp,
        horizontal = 25.dp
    ),
    context: Context
) {
    BackHandler {

    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        SettingsTopBar(
            destination = AppDestination.SettingsTopBarDestinations.SETTINGS,
            onIconClick = {

            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
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

            item {
                SettingsOptionPane(
                    icon = R.drawable.brush,
                    title = R.string.style,
                    subtitle = R.string.styles_cringe,
                    onClick = {
                        navigator.navigate(StyleScreenDestination, onlyIfResumed = true)
                    }
                )
            }
        }
    }
}