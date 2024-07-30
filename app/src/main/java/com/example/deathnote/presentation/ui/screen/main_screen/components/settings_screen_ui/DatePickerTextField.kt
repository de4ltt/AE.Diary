package com.example.deathnote.presentation.ui.screen.main_screen.components.settings_screen_ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.runtime.Composable
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarTextField

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DatePickerTextField(
    title: Int,
    value: String,
    onValueChange: (String) -> Unit,
    selectableDates: SelectableDates
) {
    BottomBarTextField(
        title = title,
        value = value,
        onValueChange = onValueChange,
        isDatePicker = true,
        previousDateSelector = datePickerSettings.previousDateSelector,
        futureDatesSelector = datePickerSettings.futureDatesSelector
    )
}