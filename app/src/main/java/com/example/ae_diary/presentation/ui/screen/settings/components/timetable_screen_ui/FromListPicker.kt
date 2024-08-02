package com.example.ae_diary.presentation.ui.screen.settings.components.timetable_screen_ui

import androidx.annotation.StringRes
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun <T> FromListPicker(
    list: List<T>,
    @StringRes title: Int,
    @StringRes value: Int,
    onEvent: (T) -> Unit
) {

    var curListPosition by remember {
        mutableIntStateOf(0)
    }

    Column(
        modifier = Modifier
            .wrapContentSize()
    ) {

        Text(
            text = stringResource(id = title),
            style = DeathNoteTheme.typography.textFieldTitle,
            color = DeathNoteTheme.colors.inverse
        )


        Box(
            modifier = Modifier
                .height(50.dp)
                .fillMaxWidth()
                .clip(
                    shape = DeathNoteTheme.shapes.rounded12
                )
                .background(
                    color = DeathNoteTheme.colors.baseBackground
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = {
                        curListPosition++
                        onEvent(list[(curListPosition) % list.size])
                    }
                ),
            contentAlignment = Alignment.Center
        ) {
            Crossfade(targetState = value) {
                Text(
                    text = stringResource(id = it).uppercase(),
                    fontSize = 15.sp,
                    color = DeathNoteTheme.colors.inverse,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp)
                )
            }
        }

    }
}