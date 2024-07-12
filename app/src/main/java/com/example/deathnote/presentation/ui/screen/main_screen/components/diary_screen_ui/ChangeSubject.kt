package com.example.deathnote.presentation.ui.screen.main_screen.components.diary_screen_ui

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.model.state.DiaryUIState
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun ChangeSubject(
    isSubjectDismissed: Boolean,
    paddingValues: PaddingValues,
    state: DiaryUIState,
    onEvent: (DiaryUIEvent) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(paddingValues)
            .animateContentSize(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            painter = painterResource(id = R.drawable.navigate_arrow),
            contentDescription = "arrow_left",
            modifier = Modifier
                .size(15.dp)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { onEvent(DiaryUIEvent.SetPreviousDaySubject) }
                )
                .animateContentSize(),
            tint = DeathNoteTheme.colors.lightInverse
        )

        Text(
            text = state.curSubject.name,
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(1f)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            if (isSubjectDismissed)
                                onEvent(DiaryUIEvent.UnsetDaySubjectDismissed)
                            else
                                onEvent(DiaryUIEvent.SetDaySubjectDismissed)
                        }
                    )
                },
            style = DeathNoteTheme.typography.subjectCardTimetableTitle,
            color = animateColorAsState(
                targetValue = if (isSubjectDismissed) DeathNoteTheme.colors.secondary
                else DeathNoteTheme.colors.lightInverse
            ).value,
            textAlign = TextAlign.Center
        )

        Icon(
            painter = painterResource(R.drawable.navigate_arrow),
            contentDescription = "arrow_right",
            modifier = Modifier
                .size(15.dp)
                .rotate(180f)
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = { onEvent(DiaryUIEvent.SetNextDaySubject) }
                )
                .animateContentSize(),
            tint = DeathNoteTheme.colors.lightInverse
        )
    }
}
