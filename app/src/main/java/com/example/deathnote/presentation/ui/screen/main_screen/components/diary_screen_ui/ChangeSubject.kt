package com.example.deathnote.presentation.ui.screen.main_screen.components.diary_screen_ui

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.model.state.DiaryUIState

@Composable
fun ChangeSubject(
    isSubjectDismissed: Boolean,
    paddingValues: PaddingValues,
    state: DiaryUIState,
    onEvent: (DiaryUIEvent) -> Unit
) {
/*
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
                    onClick = { if (state.curSubject.name.isNotEmpty()) onEvent(DiaryUIEvent.SetPreviousDaySubject) }
                )
                .animateContentSize(),
            tint = DeathNoteTheme.colors.lightInverse
        )

        Text(
            text = state.curSubject.name.ifEmpty { stringResource(id = R.string.no_sub_tod) },
            modifier = Modifier
                .padding(horizontal = 10.dp)
                .weight(1f)
                .pointerInput(Unit) {
                    detectTapGestures(
                        onLongPress = {
                            if (state.curSubject.name.isNotEmpty()) {
                                if (isSubjectDismissed)
                                    onEvent(DiaryUIEvent.UnsetDaySubjectDismissed)
                                else
                                    onEvent(DiaryUIEvent.SetDaySubjectDismissed)
                            }
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
                    onClick = { if (state.curSubject.name.isNotEmpty()) onEvent(DiaryUIEvent.SetNextDaySubject) }
                )
                .animateContentSize(),
            tint = DeathNoteTheme.colors.lightInverse
        )
    }*/
}
