package com.example.deathnote.presentation.ui.screen.main_screen.components.settings_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun DialogButton(
    text: Int,
    color: Color,
    onClick: () -> Unit
) {
    Box(
        modifier = Modifier
            .height(50.dp)
            .fillMaxWidth()
            .clip(DeathNoteTheme.shapes.rounded12)
            .background(color)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = text),
            style = DeathNoteTheme.typography.settingsScreenItemTitle,
            color = Color.White
        )
    }
}