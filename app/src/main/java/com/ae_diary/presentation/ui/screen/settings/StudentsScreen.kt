package com.ae_diary.presentation.ui.screen.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ae_diary.R
import com.ae_diary.presentation.model.Student
import com.ae_diary.presentation.model.event.StudentUIEvent
import com.ae_diary.presentation.model.state.StudentDialogState
import com.ae_diary.presentation.navigation.AppDestination
import com.ae_diary.presentation.navigation.transition.GeneralTransition
import com.ae_diary.presentation.ui.common.NothingHere
import com.ae_diary.presentation.ui.common.TopBar
import com.ae_diary.presentation.ui.common.bottom_sheet.SettingsBottomButton
import com.ae_diary.presentation.ui.screen.settings.components.students_screen_ui.StudentBar
import com.ae_diary.presentation.ui.screen.settings.components.students_screen_ui.StudentTitledDialog
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.ae_diary.presentation.viewmodel.StudentViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = GeneralTransition::class)
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

    val lazyListState = rememberLazyListState()
    val isConstricted by remember {
        derivedStateOf { lazyListState.firstVisibleItemScrollOffset != 0 }
    }

    Column(
        modifier = Modifier
            .background(color = DeathNoteTheme.colors.baseBackground)
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        TopBar(
            destination = AppDestination.SettingsTopBarDestinations.GROUP_LIST,
            onIconClick = {
                navigator.popBackStack()
            },
            isConstricted = isConstricted
        )

        NothingHere(
            modifier = Modifier.weight(1f),
            targetState = allStudents.isEmpty(),
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 10.dp),
                state = lazyListState
            ) {
                itemsIndexed(
                    items = allStudents,
                    key = { _, item -> item.id }
                ) { index, student ->
                    StudentBar(
                        index = index + 1,
                        student = student,
                        onEvent = studentViewModel::onEvent
                    )
                }
            }
        }

        SettingsBottomButton(
            title = R.string.add_student,
            onClickAction = {
                studentViewModel.apply {
                    onEvent(StudentUIEvent.IdleStudent)
                    onEvent(
                        StudentUIEvent.ChangeDialogContent(
                            student = Student(),
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
                    )
                    onEvent(StudentUIEvent.ChangeDialogState(true))
                }

            }
        )

        StudentTitledDialog(
            state = studentDialogState,
            onEvent = studentViewModel::onEvent
        )
    }
}