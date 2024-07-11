package com.example.deathnote.presentation.ui.screen.main_screen.components.diary_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.model.state.DiaryUIState
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.util.getShortName

@Composable
fun StudentCard(
    student: Student,
    state: DiaryUIState,
    isAbsent: Boolean,
    isAbsRes: Boolean,
    onEvent: (DiaryUIEvent) -> Unit = {},
    titled: Boolean = false
) {

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
    }
}