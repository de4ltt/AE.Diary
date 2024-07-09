package com.example.deathnote.presentation.ui.cross_screen_ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDefaults
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerWithDialog(
    isPreviousDate: Boolean = false,
    previousDate: String,
    curDate: String = LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
    onDismissRequest: () -> Unit,
    onAcceptRequest: (String) -> Unit,
) {

    val datePickerState = rememberDatePickerState(selectableDates = if (!isPreviousDate) {
        object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis >= LocalDate.parse(
                    previousDate,
                    DateTimeFormatter.ofPattern("dd.MM.yyyy")
                ).toEpochDay() * 86400000 && utcTimeMillis <= LocalDate.now().toEpochDay() * 86400000
            }
        }
    } else object : SelectableDates {
            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                return utcTimeMillis <= LocalDate.now().toEpochDay() * 86400000
            }
        }
    )

    val dateMillis = datePickerState.selectedDateMillis

    var formattedDate by remember {
        mutableStateOf(curDate)
    }


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