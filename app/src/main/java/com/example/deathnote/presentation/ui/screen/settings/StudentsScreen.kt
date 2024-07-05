package com.example.deathnote.presentation.ui.screen.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.event.StudentUIEvent
import com.example.deathnote.presentation.model.event.SubjectUIEvent
import com.example.deathnote.presentation.model.state.StudentDialogState
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.SettingsBottomButton
import com.example.deathnote.presentation.ui.cross_screen_ui.SettingsTopBar
import com.example.deathnote.presentation.ui.screen.settings.components.students_screen_ui.StudentTitledDialog
import com.example.deathnote.presentation.ui.screen.settings.components.students_screen_ui.StudentBar
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.viewmodel.StudentViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun StudentsScreen(
    navigator: DestinationsNavigator,
    studentViewModel: StudentViewModel,
    paddingValues: PaddingValues = PaddingValues(
        top = 50.dp,
        start = 25.dp,
        end = 25.dp,
        bottom = 25.dp
    )
) {

    BackHandler {
        navigator.popBackStack()
    }

    val allStudents: List<Student> by studentViewModel.allStudents.collectAsStateWithLifecycle()
    val studentDialogState: StudentDialogState by studentViewModel.studentDialogState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .background(color = DeathNoteTheme.colors.baseBackground)
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        SettingsTopBar(
            destination = AppDestination.SettingsTopBarDestinations.GROUP_LIST,
            onIconClick = {
                navigator.popBackStack()
            }
        )

        LazyColumn(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(bottom = 10.dp)
        ) {
            itemsIndexed(allStudents) { index, student ->
                StudentBar(
                    index = index + 1,
                    student = student,
                    onEvent = studentViewModel::onEvent
                )
            }
        }

        SettingsBottomButton(
            title = R.string.add_student,
            onClickAction = {
                studentViewModel.run {
                    StudentUIEvent.IdleStudent
                    StudentUIEvent.ChangeDialogContent(
                        student = studentDialogState.student,
                        title = R.string.add_student,
                        onAcceptRequest = {
                            studentViewModel.onEvent(
                                StudentUIEvent.UpsertStudent(studentDialogState.student)
                            )
                            onEvent(
                                StudentUIEvent.ChangeDialogState(false)
                            )
                        },
                        onDismissRequest = {
                            studentViewModel.onEvent(
                                StudentUIEvent.ChangeDialogState(false)
                            )
                        }
                    )
                    studentViewModel.onEvent(
                        StudentUIEvent.ChangeDialogState(true)
                    )
                }

            }
        )

        StudentTitledDialog(
            state = studentDialogState,
            onEvent = studentViewModel::onEvent
        )
    }
}