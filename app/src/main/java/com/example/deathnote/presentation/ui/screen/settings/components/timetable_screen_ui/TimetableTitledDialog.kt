package com.example.deathnote.presentation.ui.screen.settings.components.timetable_screen_ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.event.SubjectUIEvent
import com.example.deathnote.presentation.model.event.TimetableUIEvent
import com.example.deathnote.presentation.model.state.SubjectDialogState
import com.example.deathnote.presentation.model.state.TimetableDialogState
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarTextField
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarWithTextFields
import com.example.deathnote.presentation.ui.theme.DarkRed
import com.example.deathnote.presentation.ui.theme.DarkYellow
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.ui.util.Validator
import com.example.deathnote.presentation.viewmodel.TimetableViewModel

@Composable
fun TimetableTitledDialog(
    allSubjects: List<Subject>,
    state: TimetableDialogState,
    onEvent: (TimetableUIEvent) -> Unit
) {

    state.apply {

        if (isShown)
            BottomBarWithTextFields(
                title = R.string.add_subject,
                onAcceptRequest = {
                    onEvent(
                        TimetableUIEvent.UpsertTimetable(
                            Timetable(
                                subjectId = subject.id,
                                dayOfWeek = selectedDay,
                                startTime = startTime,
                                endTime = endTime
                            )
                        )
                    )
                    onEvent(TimetableUIEvent.ChangeDialogState(false))
                },
                onDismissRequest = { onEvent(TimetableUIEvent.ChangeDialogState(false)) },
                isActive = true,
                content = {

                    BottomBarTextField(
                        title = R.string.subject,
                        icon = {
                            Box(
                                modifier = Modifier.width(40.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(
                                        id = if (subject.type == "lk") R.string.lk
                                        else R.string.pr
                                    ).uppercase(),
                                    modifier = Modifier,
                                    color = if (subject.type == "lk") DarkRed else DarkYellow,
                                    style = DeathNoteTheme.typography.textFieldTitle
                                )
                            }
                        },
                        value = subject.name,
                        isCentered = false,
                        innerTitle = R.string.choose_subject,
                        isActive = false,
                        onClick = {
                            onEvent(TimetableUIEvent.ChangeDialogSubjectPickerState(true))
                        }
                    )

                }
            )

        if (isSubjectPickerShown)
            SubjectSelectMenu(
                allSubjects = allSubjects,
                onDismissRequest = { onEvent(TimetableUIEvent.ChangeDialogSubjectPickerState(false)) },
                onSelect = {
                    onEvent(TimetableUIEvent.ChangeDialogSubject(it))
                    onEvent(TimetableUIEvent.ChangeDialogSubjectPickerState(false))
                }
            )
    }
}