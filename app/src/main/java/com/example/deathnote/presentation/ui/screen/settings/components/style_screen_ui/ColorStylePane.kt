package com.example.deathnote.presentation.ui.screen.settings.components.style_screen_ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.ui.theme.util.adjust
import com.example.deathnote.presentation.ui.theme.util.isDarkMode

@OptIn(ExperimentalStdlibApi::class)
@Composable
fun ColorStylePane(
    color: Color,
    colorName: String,
    onClick: () -> Unit,
    onChange: (Color) -> Unit
) {

    val cardSelectionColor by animateColorAsState(
        targetValue = color,
        label = "card_sel_color"
    )

    val textSelectionColor by animateColorAsState(
        targetValue = color.adjust(
            if (isDarkMode()) 1.5f else 0.7f
        ),
        label = "card_text_color"
    )

    val borderSelectionColor by animateColorAsState(
        targetValue = color.adjust(
            if (isDarkMode()) 1.3f else 0.9f
        ),
        label = "border_color"
    )

    val cardColor by animateColorAsState(targetValue = DeathNoteTheme.colors.regularBackground)

    val mainTextColor by animateColorAsState(targetValue = DeathNoteTheme.colors.inverse)

    Row(
        modifier = Modifier
            .shadow(
                elevation = 2.dp,
                shape = DeathNoteTheme.shapes.rounded12,
                ambientColor = cardColor
            )
            .fillMaxWidth()
            .aspectRatio(1f / 1f)
            .clip(DeathNoteTheme.shapes.rounded12)
            .pointerInput(Unit) {
                detectTapGestures {
                    onClick()
                }
            }
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.8f)
                .background(
                    color = cardColor
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(10.dp),
                verticalArrangement = Arrangement.SpaceBetween,
                horizontalAlignment = Alignment.Start
            ) {
                Column {
                    Text(
                        text = stringResource(id = R.string.color),
                        style = DeathNoteTheme.typography.settingsScreenItemTitle,
                        color = mainTextColor
                    )

                    Crossfade(targetState = color, label = "text_anim") {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = DeathNoteTheme.typography.settingsScreenItemTitle.fontSize,
                                        fontFamily = DeathNoteTheme.typography.settingsScreenItemTitle.fontFamily,
                                        color = mainTextColor
                                    )
                                ) {
                                    append("#")
                                }

                                withStyle(
                                    style = SpanStyle(
                                        fontSize = DeathNoteTheme.typography.settingsScreenItemTitle.fontSize,
                                        fontFamily = DeathNoteTheme.typography.settingsScreenItemTitle.fontFamily,
                                        color = textSelectionColor
                                    )
                                ) {
                                    append("${
                                        it.toArgb().toHexString(format = HexFormat.UpperCase)
                                            .subSequence(2, 8)
                                    }")
                                }
                            }
                        )
                    }
                }

                Text(
                    text = colorName,
                    color = DeathNoteTheme.colors.lightInverse,
                    style = DeathNoteTheme.typography.itemCardTitle,
                    fontStyle = FontStyle.Italic
                )
            }
        }

        Box(
            modifier = Modifier
                .fillMaxHeight()
                .weight(0.2f)
                .background(
                    color = cardSelectionColor
                )
                .drawBehind {
                    drawLine(
                        start = Offset.Zero,
                        end = Offset(0f, Float.MAX_VALUE),
                        color = borderSelectionColor
                    )
                }
        )
    }
}