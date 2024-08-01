package com.example.deathnote.presentation.util

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import com.example.deathnote.presentation.model.interfaces.CertificateDatePickerState
import com.example.deathnote.presentation.model.interfaces.SettingsDatePickerState
import com.example.deathnote.presentation.util.TimeFormatter.dateFormatter
import com.example.deathnote.presentation.util.TimeFormatter.nowDate
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
object SelectableDatesUtil {
    fun createCertificateSelectableDates(
        certificateDatePickerState: CertificateDatePickerState,
        previousDate: String,
        semesterTime: Pair<LocalDate, LocalDate>
    ): SelectableDates {
        val semesterStart = semesterTime.first.toEpochDay() * 86400000
        val semesterEnd = semesterTime.second.toEpochDay() * 86400000
        val now = nowDate.toEpochDay() * 86400000
        val previousDateEpoch = LocalDate.parse(previousDate, dateFormatter).toEpochDay() * 86400000

        return when (certificateDatePickerState) {

            CertificateDatePickerState.START -> object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis <= now
                            && utcTimeMillis in semesterStart..semesterEnd
                }
            }

            CertificateDatePickerState.END -> object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis in previousDateEpoch..now
                            && utcTimeMillis in semesterStart..semesterEnd
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
        val previousDatePlus14 = LocalDate.parse(
            previousDate,
            dateFormatter
        ).plusDays(14).toEpochDay() * 86400000
        val now = nowDate.toEpochDay() * 86400000

        return when (settingsDatePickerState) {

            SettingsDatePickerState.START -> object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis >= now
                }
            }

            SettingsDatePickerState.END -> object : SelectableDates {
                override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                    return utcTimeMillis >= previousDatePlus14
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