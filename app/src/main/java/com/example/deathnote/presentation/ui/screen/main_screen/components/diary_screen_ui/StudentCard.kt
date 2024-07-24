package com.example.deathnote.presentation.ui.screen.main_screen.components.diary_screen_ui

import androidx.compose.runtime.Composable
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.model.state.DiaryUIState

@Composable
fun StudentCard(
    student: Student,
    state: DiaryUIState,
    isAbsent: Boolean,
    isAbsRes: Boolean,
    onEvent: (DiaryUIEvent) -> Unit = {},
    titled: Boolean = false
) {
/*
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f)
        ) {
            if (titled)
                Text(
                    text = stringResource(id = R.string.student),
                    style = DeathNoteTheme.typography.textFieldTitle,
                    color = DeathNoteTheme.colors.inverse
                )

            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .clip(
                        shape = DeathNoteTheme.shapes.rounded12
                    )
                    .background(DeathNoteTheme.colors.regularBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = student.getShortName().uppercase(),
                    style = DeathNoteTheme.typography.settingsScreenItemTitle,
                    color = DeathNoteTheme.colors.inverse,
                    fontStyle = FontStyle.Normal
                )
            }
        }

        Column {
            if (titled)
                Text(
                    text = stringResource(id = R.string.res),
                    style = DeathNoteTheme.typography.textFieldTitle,
                    color = DeathNoteTheme.colors.inverse
                )

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(
                        shape = DeathNoteTheme.shapes.rounded12
                    )
                    .background(DeathNoteTheme.colors.regularBackground)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            if (isAbsRes) onEvent(
                                DiaryUIEvent.SetDaySubjectStudentPresentRespectful(
                                    student = student,
                                    daySubject = state.curSubject,
                                    date = state.date,
                                )
                            )
                            else onEvent(
                                DiaryUIEvent.SetDaySubjectStudentAbsentRespectful(
                                    student = student,
                                    daySubject = state.curSubject,
                                    date = state.date,
                                )
                            )
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isAbsRes) stringResource(id = R.string.absent_respectful) else "",
                    style = DeathNoteTheme.typography.settingsScreenItemTitle,
                    color = DeathNoteTheme.colors.inverse,
                    fontStyle = FontStyle.Normal
                )
            }
        }

        Column {
            if (titled)
                Text(
                    text = stringResource(id = R.string.absence),
                    style = DeathNoteTheme.typography.textFieldTitle,
                    color = DeathNoteTheme.colors.inverse
                )

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(
                        shape = DeathNoteTheme.shapes.rounded12
                    )
                    .background(DeathNoteTheme.colors.regularBackground)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() },
                        onClick = {
                            if (isAbsent)
                                onEvent(
                                    DiaryUIEvent.SetDaySubjectStudentPresent(
                                        student = student,
                                        daySubject = state.curSubject,
                                        date = state.date,
                                    )
                                )
                            else
                                onEvent(
                                    DiaryUIEvent.SetDaySubjectStudentAbsent(
                                        student = student,
                                        daySubject = state.curSubject,
                                        date = state.date,
                                    )
                                )
                        }
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = if (isAbsent) stringResource(id = R.string.absent) else "",
                    style = DeathNoteTheme.typography.settingsScreenItemTitle,
                    color = DeathNoteTheme.colors.inverse,
                    fontStyle = FontStyle.Normal
                )
            }
        }
    }*/
}