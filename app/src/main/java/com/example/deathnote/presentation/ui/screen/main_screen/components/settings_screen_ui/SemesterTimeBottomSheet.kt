package com.example.deathnote.presentation.ui.screen.main_screen.components.settings_screen_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.event.TimetableUIEvent
import com.example.deathnote.presentation.model.interfaces.SettingsDatePickerState
import com.example.deathnote.presentation.model.state.TimetableUIState
import com.example.deathnote.presentation.ui.cross_screen_ui.bottom_sheet.BottomBarWithTextFields

@Composable
fun SemesterTimeBottomSheet(
    state: TimetableUIState,
    onEvent: (TimetableUIEvent) -> Unit
) {
    BottomBarWithTextFields(
        title = R.string.choose_semester_period,
        isActive = true,
        onAcceptRequest = {
            onEvent(TimetableUIEvent.ChangeSemesterTime)
            onEvent(TimetableUIEvent.ChangeSettingsScreenBottomSheetState)
        },
        onDismissRequest = {
            onEvent(TimetableUIEvent.ChangeSettingsScreenBottomSheetState)
        },
        content = {
            Column(verticalArrangement = Arrangement.spacedBy(15.dp)) {
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    columns = GridCells.Fixed(2),
                    horizontalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    item {
                        SettingsDatePickerTextField(
                            title = R.string.start_date,
                            value = state.settingsBottomSheetStartDate,
                            onClick = {
                                onEvent(
                                    TimetableUIEvent.ChangeSettingsScreenBottomSheetDatePickerState(
                                        SettingsDatePickerState.START
                                    )
                                )
                            }
                        )
                    }
                    item {
                        SettingsDatePickerTextField(
                            title = R.string.end_date,
                            value = state.settingsBottomSheetEndDate,
                            onClick = {
                                onEvent(
                                    TimetableUIEvent.ChangeSettingsScreenBottomSheetDatePickerState(
                                        SettingsDatePickerState.END
                                    )
                                )
                            }
                        )
                    }
                }

                WeekTypeSelector(
                    selectedWeekType = state.settingsBottomSheetFirstWeekType,
                    onEvent = { onEvent(TimetableUIEvent.SettingsBottomSheetChangeFirstWeekType) }
                )

                HolidaySelector(
                    state = state,
                    onEvent = onEvent
                )
            }
        }
    )
}