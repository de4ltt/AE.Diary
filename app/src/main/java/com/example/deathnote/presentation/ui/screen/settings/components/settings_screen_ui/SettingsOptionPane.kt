package com.example.deathnote.presentation.ui.screen.settings.components.settings_screen_ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.ui.theme.SemiLightGray
import com.example.deathnote.presentation.ui.theme.SoftGray
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun SettingsOptionPane(
    onClick: () -> Unit = { },
    color: Color = DeathNoteTheme.colors.regularBackground,
    isDarkThemePane: Boolean = false,
    @DrawableRes icon: Int,
    @StringRes title: Int,
    @StringRes subtitle: Int
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed by interactionSource.collectIsPressedAsState()

    val paneScale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1f,
        label = "paneScale"
    )

    val backgroundColor by animateColorAsState(targetValue = DeathNoteTheme.colors.primaryBackground)

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .scale(paneScale)
            .shadow(
                elevation = 4.dp,
                shape = DeathNoteTheme.shapes.rounded12,
                ambientColor = DeathNoteTheme.colors.regularBackground,
                clip = false
            )
            .background(
                color = color,
                shape = DeathNoteTheme.shapes.rounded12
            )
            .clickable(
                indication = null,
                interactionSource = interactionSource,
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(15.dp)
                .size(60.dp)
                .clip(CircleShape)
                .background(backgroundColor),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "icon",
                modifier = Modifier
                    .size(30.dp),
                tint = DeathNoteTheme.colors.primary
            )
        }

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(end = 15.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Text(
                text = stringResource(id = title),
                style = DeathNoteTheme.typography.settingsScreenItemTitle,
                color = if (isDarkThemePane) SoftGray else DeathNoteTheme.colors.inverse
            )

            Text(
                text = stringResource(id = subtitle),
                style = DeathNoteTheme.typography.settingsScreenItemSubtitle,
                color = if (isDarkThemePane) SemiLightGray else DeathNoteTheme.colors.lightInverse
            )
        }

    }
}