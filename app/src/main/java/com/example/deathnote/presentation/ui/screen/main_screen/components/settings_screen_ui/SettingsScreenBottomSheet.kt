package com.example.deathnote.presentation.ui.screen.main_screen.components.settings_screen_ui

import androidx.compose.runtime.Composable
import com.example.deathnote.presentation.model.event.TimetableUIEvent
import com.example.deathnote.presentation.model.state.TimetableUIState

@Composable
fun SettingsScreenBottomSheet(
    state: TimetableUIState,
    onEvent: (TimetableUIEvent) -> Unit
) {
    if (state.settingsBottomSheetState) {
        if (!state.isSemesterTimeSet) {
            SemesterTimeBottomSheet(
                state = state,
                onEvent = onEvent
            )
        } else {
            SemesterTimeDialog(
                onDismissRequest = { onEvent(TimetableUIEvent.ChangeSettingsScreenBottomSheetState) },
                onConfirmRequest = {
                    onEvent(TimetableUIEvent.IdleSemesterTime)
                    onEvent(TimetableUIEvent.ChangeSettingsScreenBottomSheetState)
                }
            )
        }
    }
}
