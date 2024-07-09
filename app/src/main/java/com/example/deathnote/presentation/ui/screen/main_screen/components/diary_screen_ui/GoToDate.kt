package com.example.deathnote.presentation.ui.screen.main_screen.components.diary_screen_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun GoToDate(
    state: DiaryScreenUIState,
    onEvent: (DiaryScreenUIEvent) -> Unit
) {

    DatePickerDialog(
        onDismissRequest = onDismissRequest,
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
                            formattedDate = if (dateMillis != null) {
                                val date = Date(dateMillis)
                                val format = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
                                format.format(date)
                            } else {
                                curDate
                            }
                            onAcceptRequest(formattedDate)
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
                state = datePickerState,

                )
        }
    )
}