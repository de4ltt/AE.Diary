package com.example.deathnote.presentation.ui.screen.settings.components.timetable_screen_ui

import android.annotation.SuppressLint
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.event.TimetableUIEvent
import com.example.deathnote.presentation.model.state.TimetableDialogState
import com.example.deathnote.presentation.model.util.DayOfWeek
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarTextField
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarWithTextFields
import com.example.deathnote.presentation.ui.theme.Black
import com.example.deathnote.presentation.ui.theme.DarkRed
import com.example.deathnote.presentation.ui.theme.DarkYellow
import com.example.deathnote.presentation.ui.theme.White
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@SuppressLint("SuspiciousIndentation")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TimetableTitledDialog(
    allSubjects: List<Subject>,
    state: TimetableDialogState,
    onEvent: (TimetableUIEvent) -> Unit
) {
    state.apply {

    val timePickerState = rememberTimePickerState(
        is24Hour = true
    )

        if (isShown)
            BottomBarWithTextFields(
                title = R.string.add_subject,
                onAcceptRequest = {
                    onEvent(
                        TimetableUIEvent.UpsertTimetable(
                            Timetable(
                                subjectId = subject.id,
                                dayOfWeek = "${selectedWeekType}_$selectedDay",
                                startTime = startTime,
                                endTime = endTime
                            )
                        )
                    )
                    onEvent(TimetableUIEvent.ChangeDialogState(false))
                },
                onDismissRequest = { onEvent(TimetableUIEvent.ChangeDialogState(false)) },
                isActive = subject.name.isNotEmpty(),
                content = {

                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
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

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.wrapContentHeight(),
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            item {
                                FromListPicker(
                                    list = DayOfWeek.entries.map { it.code },
                                    title = R.string.day_of_week,
                                    value = DayOfWeek.entries[selectedDay - 1].title,
                                    onEvent = {
                                        onEvent(TimetableUIEvent.ChangeDialogDayOfWeek(it.toInt()))
                                    }
                                )
                            }
                            item {
                                FromListPicker(
                                    list = DayOfWeek.entries.map { it.code },
                                    title = R.string.week_type,
                                    value = if (selectedWeekType == "O") R.string.odd_week else R.string.even_week,
                                    onEvent = {
                                        onEvent(TimetableUIEvent.ChangeSelectedWeekType)
                                    }
                                )
                            }

                            item {
                                TimeTextPicker(
                                    isStartTime = true,
                                    onEvent = onEvent,
                                    value = state.startTime,
                                    title = R.string.start_time
                                )
                            }

                            item {
                                TimeTextPicker(
                                    isStartTime = false,
                                    onEvent = onEvent,
                                    value = state.endTime,
                                    title = R.string.end_time
                                )
                            }
                        }
                    }
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

        if (isTimePickerShown)
            TimePickerDialog(
                title = stringResource(id = R.string.select_time),
                onDismissRequest = { onEvent(TimetableUIEvent.ChangeTimePickerState(false)) },
                confirmButton = {
                    Text(
                        modifier = Modifier
                            .padding(
                                bottom = 20.dp,
                                end = 15.dp
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    val hour = "0${timePickerState.hour}".takeLast(2)
                                    val minute = "0${timePickerState.minute}".takeLast(2)

                                    val hrMin = "$hour:$minute"

                                    onEvent(
                                        if (pick == "Start") {
                                            TimetableUIEvent.ChangeDialogStartTime(hrMin)
                                        } else TimetableUIEvent.ChangeDialogEndTime(hrMin)
                                    )

                                    onEvent(TimetableUIEvent.ChangeTimePickerState(false))
                                }
                            ),
                        text = stringResource(id = R.string.ready),
                        style = DeathNoteTheme.typography.settingsScreenItemTitle,
                        color = DeathNoteTheme.colors.primary
                    )
                },
                dismissButton = {
                    Text(
                        modifier = Modifier
                            .padding(
                                bottom = 20.dp,
                                end = 15.dp
                            )
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    onEvent(TimetableUIEvent.ChangeTimePickerState(false))
                                }
                            ),
                        text = stringResource(id = R.string.cancel),
                        style = DeathNoteTheme.typography.settingsScreenItemTitle,
                        color = DeathNoteTheme.colors.lightInverse
                    )
                }
            ) {
                TimePicker(
                    colors = TimePickerDefaults.colors(
                        clockDialColor = DeathNoteTheme.colors.primaryBackground,
                        clockDialSelectedContentColor = White,
                        clockDialUnselectedContentColor = Black,
                        selectorColor = DeathNoteTheme.colors.primary,
                        timeSelectorSelectedContainerColor = DeathNoteTheme.colors.primary,
                        timeSelectorUnselectedContainerColor = DeathNoteTheme.colors.primaryBackground,
                        timeSelectorSelectedContentColor = White,
                    ),
                    state = timePickerState
                )
            }
    }
}