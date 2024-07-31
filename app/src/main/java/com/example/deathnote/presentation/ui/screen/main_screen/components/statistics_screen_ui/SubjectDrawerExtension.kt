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
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.event.StatisticsUIEvent
import com.example.deathnote.presentation.model.interfaces.StatisticsMode
import com.example.deathnote.presentation.model.state.StatisticsUIState
import com.example.deathnote.presentation.ui.screen.settings.components.timetable_screen_ui.SubjectMenuBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SubjectDrawerExtension(
    state: StatisticsUIState,
    onEvent: (StatisticsUIEvent) -> Unit,
    subjectList: List<Subject>
) {
    state.apply {
        if (isSubjectDrawerOpen) {

            ModalBottomSheet(
                onDismissRequest = {
                    onEvent(StatisticsUIEvent.ChangeSubjectDrawerState)
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
                    if (subjectList.size > 1)
                        item {
                            SubjectMenuBar(
                                subject = Subject(
                                    name = stringResource(id = R.string.subjects),
                                    type = stringResource(id = R.string.all)
                                ),
                                onSelect = {
                                    onEvent(StatisticsUIEvent.ChangeMode(StatisticsMode.OneStudentManySubjects))
                                    onEvent(StatisticsUIEvent.ChangeSubjectDrawerState)
                                }
                            )
                        }

                    items(subjectList) { subject ->
                        SubjectMenuBar(
                            subject = subject,
                            onSelect = {
                                onEvent(StatisticsUIEvent.ChangeSubject(subject))
                                onEvent(StatisticsUIEvent.ChangeMode(StatisticsMode.ManyStudentsOneSubject))
                                onEvent(StatisticsUIEvent.ChangeSubjectDrawerState)
                            }
                        )
                    }
                }
            }
        }
    }
}