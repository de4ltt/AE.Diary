package com.example.deathnote.presentation.ui.screen.main_screen.components.settings_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.event.TimetableUIEvent
import com.example.deathnote.presentation.model.state.TimetableUIState
import com.example.deathnote.presentation.model.util.DayOfWeek
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun DayBox(
    day: DayOfWeek,
    state: TimetableUIState,
    onEvent: (TimetableUIEvent) -> Unit
) {
    state.apply {


        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(
                    DeathNoteTheme.shapes.rounded12
                )
                .background(
                    if (settingsBottomSheetHolidays.contains(day))
                        DeathNoteTheme.colors.primaryBackground
                    else
                        DeathNoteTheme.colors.baseBackground
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        if (settingsBottomSheetHolidays.contains(day))
                            onEvent(TimetableUIEvent.SettingsBottomSheetDeleteHoliday(day))
                        else
                            onEvent(TimetableUIEvent.SettingsBottomSheetAddHoliday(day))
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(
                    when (day) {
                        DayOfWeek.MONDAY -> R.string.monday
                        DayOfWeek.TUESDAY -> R.string.tuesday
                        DayOfWeek.WEDNESDAY -> R.string.wednesday
                        DayOfWeek.THURSDAY -> R.string.thursday
                        DayOfWeek.FRIDAY -> R.string.friday
                        DayOfWeek.SATURDAY -> R.string.saturday
                        DayOfWeek.SUNDAY -> R.string.sunday
                    }
                ).slice(0..1).uppercase(),
                fontSize = 15.sp,
                color = DeathNoteTheme.colors.inverse,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}