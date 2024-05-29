package com.example.deathnote.presentation.ui.screen.settings.components.style_screen_ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.ui.theme.util.DeathNoteTheme

@Composable
fun StyleInterthemePane(
    onClick: () -> Unit,
    definingState: Boolean,
    settingValue: Int = R.string.is_dark_theme,
    isOnValue: Int = R.string.is_on,
    isOffValue: Int = R.string.is_off
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                elevation = 2.dp,
                shape = DeathNoteTheme.shapes.rounded12,
                ambientColor = DeathNoteTheme.colors.regularBackground
            )
            .background(
                color = DeathNoteTheme.colors.inverseBackground
            )
            .clip(DeathNoteTheme.shapes.rounded12)
            .pointerInput(Unit) {
                detectTapGestures {
                    onClick()
                }
            }
    ) {
        Column(
            modifier = Modifier
                .padding(10.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Crossfade(targetState = settingValue, label="style_text") {
                Text(
                    text = stringResource(id = it),
                    style = DeathNoteTheme.typography.settingsScreenItemTitle,
                    color = DeathNoteTheme.colors.regular
                )
            }

            Text(
                text = stringResource(id = if (definingState) isOnValue else isOffValue),
                color = DeathNoteTheme.colors.lightInverse,
                style = DeathNoteTheme.typography.itemCardTitle,
                fontStyle = FontStyle.Italic
            )
        }
    }
}