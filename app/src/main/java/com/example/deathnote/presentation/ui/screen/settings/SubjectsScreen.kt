package com.example.deathnote.presentation.ui.screen.settings

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
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.SubjectType
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.SettingsTopBar
import com.example.deathnote.presentation.ui.screen.settings.composable.students_screen_ui.StudentBar
import com.example.deathnote.presentation.ui.screen.settings.composable.subjects_screen_ui.SubjectBar
import com.example.deathnote.presentation.ui.screen.settings.destinations.SettingsScreenDestination
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SubjectsScreen(
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(
        vertical = 50.dp,
        horizontal = 25.dp
    )
) {

    BackHandler {
        navigator.popBackStack()
    }

    Column(
        modifier = Modifier
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        SettingsTopBar(
            destination = AppDestination.SettingsTopBarDestinations.SUBJECTS,
            onIconClick = {
                navigator.popBackStack()
            }
        )

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            item {
                SubjectBar(index = 1, subject = Subject(1, "Математический анализ", SubjectType.LECTURE))
            }

            item {
                SubjectBar(index = 2, subject = Subject(2,"Теория вероятностей и математическая статистика", SubjectType.PRACTICE))
            }
        }
    }
}