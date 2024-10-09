package com.ae_diary.presentation.ui.screen.main_screen.components.certificates_screen_ui

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
import com.ae_diary.presentation.model.enums.CertificateDatePickerState
import com.ae_diary.presentation.model.event.CertificateUIEvent
import com.ae_diary.presentation.model.state.CertificateUIState
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.ae_diary.presentation.util.SelectableDates.createCertificateSelectableDates
import com.ae_diary.presentation.util.TimeFormatter.dateFormatter
import com.ae_diary.presentation.util.TimeFormatter.formatSelectedDate
import com.ae_diary.presentation.util.TimeFormatter.nowDate
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CertificatesDatePickerWithDialog(
    state: CertificateUIState,
    onEvent: (CertificateUIEvent) -> Unit,
    semesterTime: Pair<LocalDate, LocalDate>
) {

    state.apply {

        if (bottomSheetDatePickerState != CertificateDatePickerState.NONE) {

            val selectableDates =
                createCertificateSelectableDates(bottomSheetDatePickerState, endDate, semesterTime)

            val datePickerState = rememberDatePickerState(selectableDates = selectableDates)

            val dateMillis = datePickerState.selectedDateMillis

            DatePickerDialog(
                onDismissRequest = {
                    onEvent(
                        CertificateUIEvent.ChangeCertificateDatePickerState(
                            CertificateDatePickerState.NONE
                        )
                    )
                },
                confirmButton = {
                    Text(
                        modifier = Modifier
                            .padding(bottom = 20.dp, end = 15.dp)
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    if (bottomSheetDatePickerState == CertificateDatePickerState.START)
                                        onEvent(
                                            CertificateUIEvent.ChangeStartDate(
                                                formatSelectedDate(
                                                    dateMillis, nowDate.format(
                                                        dateFormatter
                                                    )
                                                )
                                            )
                                        )
                                    else
                                        onEvent(
                                            CertificateUIEvent.ChangeEndDate(
                                                formatSelectedDate(
                                                    dateMillis, nowDate.format(
                                                        dateFormatter
                                                    )
                                                )
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
