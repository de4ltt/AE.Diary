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
import com.ae_diary.presentation.model.Subject
import com.ae_diary.presentation.model.event.SubjectUIEvent
import com.ae_diary.presentation.navigation.AppDestination
import com.ae_diary.presentation.navigation.transition.GeneralTransition
import com.ae_diary.presentation.ui.common.NothingHere
import com.ae_diary.presentation.ui.common.TopBar
import com.ae_diary.presentation.ui.common.bottom_sheet.SettingsBottomButton
import com.ae_diary.presentation.ui.screen.settings.components.subjects_screen_ui.SubjectBar
import com.ae_diary.presentation.ui.screen.settings.components.subjects_screen_ui.SubjectTitledDialog
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.ae_diary.presentation.viewmodel.SubjectViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = GeneralTransition::class)
@Composable
fun SubjectsScreen(
    navigator: DestinationsNavigator,
    subjectViewModel: SubjectViewModel,
    paddingValues: PaddingValues = PaddingValues(
        top = 50.dp,
        start = 25.dp,
        end = 25.dp,
        bottom = 25.dp
    )
) {

    val allSubjects by subjectViewModel.allSubjects.collectAsStateWithLifecycle()
    val subjectDialogState by subjectViewModel.subjectDialogState.collectAsStateWithLifecycle()

    val lazyListState = rememberLazyListState()
    val isConstricted by remember {
        derivedStateOf { lazyListState.firstVisibleItemScrollOffset != 0 }
    }

    BackHandler {
        navigator.popBackStack()
    }

    Column(
        modifier = Modifier
            .background(color = DeathNoteTheme.colors.baseBackground)
            .padding(paddingValues)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        TopBar(
            destination = AppDestination.SettingsTopBarDestinations.SUBJECTS,
            onIconClick = {
                navigator.popBackStack()
            },
            isConstricted = isConstricted
        )

        NothingHere(
            modifier = Modifier.weight(1f),
            targetState = allSubjects.isEmpty()
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 10.dp),
                state = lazyListState
            ) {
                itemsIndexed(
                    items = allSubjects,
                    key = { _, item -> item.id }
                ) { index, subject ->
                    SubjectBar(
                        index + 1,
                        subject,
                        onEvent = subjectViewModel::onEvent
                    )
                }
            }
        }

            SettingsBottomButton(
                title = R.string.add_subject,
                onClickAction = {
                    subjectViewModel.apply {
                        onEvent(SubjectUIEvent.IdleSubject)
                        onEvent(SubjectUIEvent.ChangeDialogContent(
                            subject = Subject(),
                            title = R.string.add_subject,
                            onAcceptRequest = {
                                onEvent(
                                    SubjectUIEvent.UpsertSubject(subjectDialogState.subject)
                                )
                                onEvent(
                                    SubjectUIEvent.ChangeDialogState(false)
                                )
                            },
                            onDismissRequest = {
                                onEvent(
                                    SubjectUIEvent.ChangeDialogState(false)
                                )
                            }
                        ))
                        onEvent(
                            SubjectUIEvent.ChangeDialogState(true)
                        )
                    }

                }
            )
        }

        SubjectTitledDialog(
            state = subjectDialogState,
            onEvent = subjectViewModel::onEvent
        )
    }