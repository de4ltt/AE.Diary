package com.example.ae_diary.presentation.ui.screen.settings

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
import com.example.ae_diary.R
import com.example.ae_diary.presentation.model.Subject
import com.example.ae_diary.presentation.model.event.SubjectUIEvent
import com.example.ae_diary.presentation.navigation.AppDestination
import com.example.ae_diary.presentation.navigation.transition.GeneralTransition
import com.example.ae_diary.presentation.ui.cross_screen_ui.NothingHere
import com.example.ae_diary.presentation.ui.cross_screen_ui.bottom_sheet.SettingsBottomButton
import com.example.ae_diary.presentation.ui.cross_screen_ui.top_bar.SettingsTopBar
import com.example.ae_diary.presentation.ui.screen.settings.components.subjects_screen_ui.SubjectBar
import com.example.ae_diary.presentation.ui.screen.settings.components.subjects_screen_ui.SubjectTitledDialog
import com.example.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.example.ae_diary.presentation.viewmodel.SubjectViewModel
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