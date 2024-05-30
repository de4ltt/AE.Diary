package com.example.deathnote.presentation.ui.screen.settings.components.language_screen_ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsDraggedAsState
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.model.Language
import com.example.deathnote.presentation.ui.theme.Black
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun LanguageBar(
    onClick: () -> Unit,
    language: Language,
    isChosen: Boolean
) {

    val interactionSource = remember { MutableInteractionSource() }
    val isPressed =
        (interactionSource.collectIsPressedAsState().value || interactionSource.collectIsDraggedAsState().value)

    val paneScale by animateFloatAsState(
        targetValue = if (isPressed) 0.97f else 1f,
        label = "paneScale"
    )

    val brush = Brush.linearGradient(
        colors = listOf(
            animateColorAsState(
                targetValue = if (isChosen) language.dominantColors[0] else DeathNoteTheme.colors.regularBackground,
                animationSpec = tween(100)
            ).value,
            animateColorAsState(
                targetValue = if (isChosen) language.dominantColors[1] else DeathNoteTheme.colors.regularBackground,
                animationSpec = tween(500)
            ).value,
            animateColorAsState(
                targetValue = if (isChosen) language.dominantColors[2] else DeathNoteTheme.colors.regularBackground,
                animationSpec = tween(1000)
            ).value
        ),
        start = Offset.Zero,
        end = Offset.Infinite
    )

    val textColor by animateColorAsState(
        targetValue = if (isChosen) Black else DeathNoteTheme.colors.inverse,
        label = "text_color"
    )


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .scale(paneScale)
            .shadow(
                elevation = 4.dp,
                shape = DeathNoteTheme.shapes.rounded12,
                ambientColor = DeathNoteTheme.colors.regularBackground
            )
            .background(
                brush = brush,
                shape = DeathNoteTheme.shapes.rounded12
            )
            .clickable(
                indication = null,
                interactionSource = interactionSource,
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Image(
            painter = painterResource(id = language.icon),
            contentScale = ContentScale.FillHeight,
            contentDescription = "icon",
            modifier = Modifier
                .padding(15.dp)
                .size(40.dp)
                .clip(CircleShape)
        )

        Text(
            text = stringResource(id = language.title),
            style = DeathNoteTheme.typography.settingsScreenItemTitle,
            color = textColor
        )

    }
}