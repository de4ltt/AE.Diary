package com.example.deathnote.presentation.ui.screen.main_screen.components.settings_screen_ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.model.state.DiaryUIState
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun DayBox(
    day: Int,
    state: DiaryUIState,
    onEvent: (DiaryUIEvent) -> Unit
) {
    Box(
        modifier = Modifier
            .size(50.dp)
            .clip(
                DeathNoteTheme.shapes.rounded12
            )
            .background(
                if (state.holidays.contains(day))
                    DeathNoteTheme.colors.primaryBackground
                else
                    DeathNoteTheme.colors.baseBackground
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    if (state.holidays.contains(day))
                        onEvent(DiaryUIEvent.DeleteHoliday(day))
                    else
                        onEvent(DiaryUIEvent.AddHoliday(day))
                }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(
                when (day) {
                    1 -> R.string.monday
                    2 -> R.string.tuesday
                    3 -> R.string.wednesday
                    4 -> R.string.thursday
                    5 -> R.string.friday
                    6 -> R.string.saturday
                    else -> R.string.sunday
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