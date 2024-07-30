package com.example.deathnote.presentation.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import com.example.deathnote.presentation.model.interfaces.CertificateDatePickerState
import com.example.deathnote.presentation.model.interfaces.SettingsDatePickerState
import com.example.deathnote.presentation.util.TimeFormatter.dateFormatter
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
object SelectableDatesUtil {
    fun createCertificateSelectableDates(
        certificateDatePickerState: CertificateDatePickerState,
        previousDate: String
    ): SelectableDates {
        return when (certificateDatePickerState) {
            CertificateDatePickerState.START -> object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis <= LocalDate.now().toEpochDay() * 86400000
                }
            }

            CertificateDatePickerState.END -> object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    val previousDateEpoch =
                        LocalDate.parse(previousDate, dateFormatter).toEpochDay() * 86400000
                    return utcTimeMillis in previousDateEpoch..LocalDate.now()
                        .toEpochDay() * 86400000
                }
            }

            CertificateDatePickerState.NONE -> object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return false
                }
            }
        }
    }

    fun createSettingsSelectableDates(
        settingsDatePickerState: SettingsDatePickerState,
        previousDate: String
    ): SelectableDates {
        when (settingsDatePickerState) {
            SettingsDatePickerState.START -> object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis >= LocalDate.now().toEpochDay() * 86400000
                }
            }

            SettingsDatePickerState.END -> object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis >= LocalDate.parse(
                        previousDate,
                        DateTimeFormatter.ofPattern("dd.MM.yyyy")
                    ).toEpochDay() * 86400000
                }
            }

            SettingsDatePickerState.NONE -> object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return false
                }
            }
        }
    }


}