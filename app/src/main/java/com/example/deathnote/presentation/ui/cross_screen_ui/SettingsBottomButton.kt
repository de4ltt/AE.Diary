package com.example.deathnote.presentation.ui.cross_screen_ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun SettingsBottomButton(
    isActive: Boolean = true,
    @StringRes title: Int,
    onClickAction: () -> Unit
) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(
                shape = DeathNoteTheme.shapes.rounded12
            )
            .background(
                color = if (isActive) DeathNoteTheme.colors.primaryDefault else DeathNoteTheme.colors.primaryBackground
            )
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = {
                    if (isActive) onClickAction()
                }
            ),
        contentAlignment = Alignment.Center,
        content = {
            Text(
                text = stringResource(id = title),
                style = DeathNoteTheme.typography.settingsScreenItemTitle,
                color = Color.White
            )
        }
    )
}