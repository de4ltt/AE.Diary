package com.example.deathnote.presentation.ui.screen.settings.components.style_screen_ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun RedoPane(
    onClick: () -> Unit
) {
    val elementsColor by animateColorAsState(
        targetValue = DeathNoteTheme.colors.regular
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .aspectRatio(1f / 1f)
            .shadow(
                elevation = 4.dp,
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
            modifier = Modifier.fillMaxHeight().padding(10.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
                Icon(
                    painter = painterResource(id = R.drawable.redo),
                    contentDescription = "redo",
                    tint = elementsColor,
                    modifier = Modifier.size(30.dp)
                )

                Text(
                    text = stringResource(R.string.redo),
                    color = DeathNoteTheme.colors.lightInverse,
                    style = DeathNoteTheme.typography.itemCardTitle,
                    fontStyle = FontStyle.Italic
                )
        }
    }
}