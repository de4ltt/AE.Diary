package com.example.deathnote.presentation.ui.cross_screen_ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deathnote.R
import com.example.deathnote.presentation.ui.theme.SoftGray
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun TitledField(
    @StringRes title: Int,
    showTitle: Boolean = true,
    textValue: String = "",
    isCentered: Boolean = false,
    squared: Boolean = false
) {
    Column {
        if (showTitle)
            Text(
                text = stringResource(id = title),
                style = DeathNoteTheme.typography.textFieldTitle,
                color = DeathNoteTheme.colors.inverse
            )

        val modifier = if (squared) Modifier.aspectRatio(1f) else Modifier.fillMaxWidth()

        Box(
            modifier = modifier
                .height(50.dp)
                .clip(
                    shape = DeathNoteTheme.shapes.rounded12
                )
                .background(
                    color = DeathNoteTheme.colors.baseBackground
                ),
            contentAlignment = if (isCentered) Alignment.Center else Alignment.CenterStart
        ) {
            Text(
                text = textValue,
                color = DeathNoteTheme.colors.inverse,
                fontSize = 15.sp
            )
        }
    }

}