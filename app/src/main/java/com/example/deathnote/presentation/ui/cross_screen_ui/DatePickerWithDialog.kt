package com.example.deathnote.presentation.ui.cross_screen_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.event.TimetableUIEvent
import com.example.deathnote.presentation.model.interfaces.SettingsDatePickerState
import com.example.deathnote.presentation.model.state.TimetableUIState
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.util.SelectableDatesUtil.createSettingsSelectableDates
import com.example.deathnote.presentation.util.TimeFormatter.formatSelectedDate
import com.example.deathnote.presentation.util.TimeFormatter.nowDateFormatted

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWithDialog(
    state: TimetableUIState,
    onEvent: (TimetableUIEvent) -> Unit
) {

    state.apply {

        if (settingBottomSheetDatePickerState != SettingsDatePickerState.NONE) {
            val datePickerState = rememberDatePickerState(
                selectableDates = createSettingsSelectableDates(
                    settingBottomSheetDatePickerState,
                    settingsBottomSheetEndDate
                )
            )

            val dateMillis = datePickerState.selectedDateMillis

            DatePickerDialog(
                onDismissRequest = {
                    onEvent(
                        TimetableUIEvent.ChangeSettingsScreenBottomSheetState(
                            SettingsDatePickerState.NONE
                        )
                    )
                },
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
                                    val formattedDate =
                                        formatSelectedDate(dateMillis, nowDateFormatted)

                                    if (settingBottomSheetDatePickerState == SettingsDatePickerState.START)
                                        onEvent(
                                            TimetableUIEvent.ChangeBottomSheetStartTime(
                                                formattedDate
                                            )
                                        )
                                    else onEvent(
                                        TimetableUIEvent.ChangeBottomSheetEndTime(formattedDate)
                                    )
                                }
                            ),
                        text = stringResource(id = R.string.ready),
                        style = DeathNoteTheme.typography.settingsScreenItemTitle,
                        color = DeathNoteTheme.colors.primary
                    )
                },
                content = {
                    DatePicker(
                        colors = DatePickerDefaults.colors(
                            todayContentColor = DeathNoteTheme.colors.primary,
                            selectedDayContainerColor = DeathNoteTheme.colors.primary,
                            todayDateBorderColor = DeathNoteTheme.colors.primary
                        ),
                        state = datePickerState
                    )
                }
            )
        }
    }
}