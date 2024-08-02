package com.example.ae_diary.presentation.ui.screen.main_screen.components.settings_screen_ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
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
import com.example.ae_diary.R
import com.example.ae_diary.presentation.model.util.WeekType
import com.example.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun WeekTypeSelector(
    selectedWeekType: WeekType,
    onEvent: () -> Unit
) {
    Column(modifier = Modifier.wrapContentSize()) {
        Text(
            text = stringResource(id = R.string.first_week),
            style = DeathNoteTheme.typography.textFieldTitle,
            color = DeathNoteTheme.colors.inverse
        )
        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .clip(shape = DeathNoteTheme.shapes.rounded12)
                .background(color = DeathNoteTheme.colors.baseBackground)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onEvent
                ),
            contentAlignment = Alignment.Center
        ) {
            Crossfade(targetState = selectedWeekType) {
                Text(
                    text = stringResource(id = if (it == WeekType.ODD) R.string.odd else R.string.even).uppercase(),
                    fontSize = 15.sp,
                    color = DeathNoteTheme.colors.inverse,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)
                )
            }
        }
    }
}