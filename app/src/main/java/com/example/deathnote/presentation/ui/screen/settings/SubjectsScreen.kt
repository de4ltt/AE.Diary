package com.example.deathnote.presentation.ui.screen.settings

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.model.event.SubjectUIEvent
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.NothingHere
import com.example.deathnote.presentation.ui.cross_screen_ui.SettingsBottomButton
import com.example.deathnote.presentation.ui.cross_screen_ui.SettingsTopBar
import com.example.deathnote.presentation.ui.screen.settings.components.subjects_screen_ui.SubjectBar
import com.example.deathnote.presentation.ui.screen.settings.components.subjects_screen_ui.SubjectTitledDialog
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.viewmodel.DiaryViewModel
import com.example.deathnote.presentation.viewmodel.SubjectViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun SubjectsScreen(
    navigator: DestinationsNavigator,
    subjectViewModel: SubjectViewModel,
    diaryViewModel: DiaryViewModel,
    paddingValues: PaddingValues = PaddingValues(
        top = 50.dp,
        start = 25.dp,
        end = 25.dp,
        bottom = 25.dp
    )
) {

    val allSubjects by subjectViewModel.allSubjects.collectAsStateWithLifecycle()
    val subjectDialogState by subjectViewModel.subjectDialogState.collectAsStateWithLifecycle()

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

        SettingsTopBar(
            destination = AppDestination.SettingsTopBarDestinations.SUBJECTS,
            onIconClick = {
                navigator.popBackStack()
            }
        )

        if (allSubjects.isEmpty())
            Box(modifier = Modifier.weight(1f)) {
                NothingHere()
            }
        else
            LazyColumn(
                modifier = Modifier.weight(1f),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 10.dp)
            ) {
                itemsIndexed(allSubjects) { index, subject ->
                    SubjectBar(
                        index + 1,
                        subject,
                        onEvent = subjectViewModel::onEvent
                    )
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
        onEvent = subjectViewModel::onEvent,
        refreshSubjects = { /*diaryViewModel.onEvent(DiaryUIEvent.RefreshSubject)*/ }
    )
}