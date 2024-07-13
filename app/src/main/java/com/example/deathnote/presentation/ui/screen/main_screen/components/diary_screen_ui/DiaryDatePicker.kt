package com.example.deathnote.presentation.ui.screen.main_screen.components.diary_screen_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.model.state.DiaryUIState
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiaryDatePicker(
    isSelectableDate: (String) -> Boolean,
    state: DiaryUIState,
    onEvent: (DiaryUIEvent) -> Unit
) {

    val datePickerState = rememberDatePickerState(
        selectableDates = object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                val date = Date(utcTimeMillis)
                val pattern = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

                return isSelectableDate(pattern.format(date))
            }
        }
    )

    state.apply {

        if (isDatePickerOpen)
            DatePickerDialog(
                onDismissRequest = {
                    onEvent(DiaryUIEvent.ChangeDatePickerState)
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
                                    datePickerState.selectedDateMillis?.let {
                                        val date = Date(it)

                                        val formatter =
                                            SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())

                                        onEvent(DiaryUIEvent.ChangeDate(formatter.format(date)))
                                        onEvent(DiaryUIEvent.ChangeDatePickerState)
                                    }
                                }
                            ),
                        text = stringResource(id = R.string.ready),
                        style = DeathNoteTheme.typography.settingsScreenItemTitle,
                        color = DeathNoteTheme.colors.primary
                    )
                }
            ) {
                DatePicker(
                    colors = DatePickerDefaults.colors(
                        todayContentColor = DeathNoteTheme.colors.primary,
                        selectedDayContainerColor = DeathNoteTheme.colors.primary,
                        todayDateBorderColor = DeathNoteTheme.colors.primary
                    ),
                    state = datePickerState
                )
            }
    }
}