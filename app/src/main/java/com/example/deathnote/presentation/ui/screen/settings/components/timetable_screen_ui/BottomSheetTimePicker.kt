package com.example.deathnote.presentation.ui.screen.settings.components.timetable_screen_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TimePicker
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.event.TimetableUIEvent
import com.example.deathnote.presentation.model.state.TimetableUIState
import com.example.deathnote.presentation.ui.theme.Black
import com.example.deathnote.presentation.ui.theme.White
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetTimePicker(
    state: TimetableUIState,
    onEvent: (TimetableUIEvent) -> Unit
) {

    val timePickerState = rememberTimePickerState(
        is24Hour = true
    )

    if (state.bottomSheetTimePickerState)
        TimePickerDialog(
            title = stringResource(id = R.string.select_time),
            onDismissRequest = { onEvent(TimetableUIEvent.ChangeBottomSheetTimePickerState) },
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
                                    if (state.bottomSheetTimePickerStartPick == "start") {
                                        TimetableUIEvent.ChangeBottomSheetStartTime(hrMin)
                                    } else TimetableUIEvent.ChangeBottomSheetEndTime(hrMin)
                                )

                                onEvent(TimetableUIEvent.ChangeBottomSheetTimePickerState)
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
                                onEvent(TimetableUIEvent.ChangeBottomSheetTimePickerState)
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