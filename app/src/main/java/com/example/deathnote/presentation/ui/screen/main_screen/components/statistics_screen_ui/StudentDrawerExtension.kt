package com.example.deathnote.presentation.ui.screen.main_screen.components.statistics_screen_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.event.StatisticsUIEvent
import com.example.deathnote.presentation.model.interfaces.StatisticsMode
import com.example.deathnote.presentation.model.state.StatisticsUIState
import com.example.deathnote.presentation.ui.screen.main_screen.components.certificates_screen_ui.StudentMenuBar
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDrawerExtension(
    state: StatisticsUIState,
    onEvent: (StatisticsUIEvent) -> Unit,
    studentsList: List<Student>
) {
    state.apply {
        if (isStudentDrawerOpen) {

            ModalBottomSheet(
                containerColor = DeathNoteTheme.colors.regularBackground,
                onDismissRequest = {
                    onEvent(StatisticsUIEvent.ChangeStudentDrawerState)
                },
                dragHandle = {}
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(
                            top = 25.dp,
                            end = 25.dp,
                            start = 25.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 15.dp)
                ) {
                    if (studentsList.size > 1)
                        item {
                            StudentMenuBar(
                                student = Student(
                                    name = stringResource(id = R.string.all_students)
                                ),
                                onSelect = {
                                    onEvent(StatisticsUIEvent.ChangeMode(StatisticsMode.ManyStudentsOneSubject))
                                    onEvent(StatisticsUIEvent.ChangeStudentDrawerState)
                                }
                            )
                        }

                    items(studentsList) { student ->
                        StudentMenuBar(
                            student = student,
                            onSelect = {
                                onEvent(StatisticsUIEvent.ChangeStudent(student))
                                onEvent(StatisticsUIEvent.ChangeMode(StatisticsMode.OneStudentManySubjects))
                                onEvent(StatisticsUIEvent.ChangeStudentDrawerState)
                            }
                        )
                    }
                }
            }
        }
    }
}