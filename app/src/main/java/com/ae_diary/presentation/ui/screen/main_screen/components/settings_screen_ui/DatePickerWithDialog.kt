package com.ae_diary.presentation.ui.screen.main_screen.components.settings_screen_ui

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
import com.ae_diary.R
import com.ae_diary.presentation.model.enums.SettingsDatePickerState
import com.ae_diary.presentation.model.event.TimetableUIEvent
import com.ae_diary.presentation.model.state.TimetableUIState
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.ae_diary.presentation.util.SelectableDates.createSettingsSelectableDates
import com.ae_diary.presentation.util.TimeFormatter.formatSelectedDate
import com.ae_diary.presentation.util.TimeFormatter.nowDateFormatted

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
                        TimetableUIEvent.ChangeSettingsScreenBottomSheetDatePickerState(
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
                                            TimetableUIEvent.SettingsBottomSheetChangeSemesterStartTime(
                                                formattedDate
                                            )
                                        )
                                    else onEvent(
                                        TimetableUIEvent.SettingsBottomSheetChangeSemesterEndTime(formattedDate)
                                    )
                                    onEvent(
                                        TimetableUIEvent.ChangeSettingsScreenBottomSheetDatePickerState(
                                            SettingsDatePickerState.NONE
                                        )
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