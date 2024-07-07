package com.example.deathnote.presentation.ui.screen.settings.components.timetable_screen_ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.event.TimetableUIEvent
import com.example.deathnote.presentation.model.util.toFormatDayOfWeek
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun TimetableCard(
    dayOfWeek: String,
    timetable: List<Timetable>,
    getSubjectById: (Int?) -> Subject?,
    onEvent: (TimetableUIEvent) -> Unit
) {
    val sortedTimetables = timetable.sortedBy { it.startTime }

    Column(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .wrapContentHeight()
            .fillMaxWidth()
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = dayOfWeek.toFormatDayOfWeek()).uppercase(),
            style = DeathNoteTheme.typography.timeTableCard,
            color = DeathNoteTheme.colors.inverse
        )

        Crossfade(targetState = sortedTimetables) {
            LazyColumn(
                modifier = Modifier
                    .wrapContentSize()
                    .animateContentSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                itemsIndexed(it) { id, item ->
                    TimeTableSubjectCard(
                        classTime = Pair(item.startTime, item.endTime),
                        subjectScheduled = getSubjectById(item.subjectId)
                    )

                }

                if (it.size < 5)
                    item {
                        TimeTableSubjectCard(
                            classTime = Pair(null, null),
                            subjectScheduled = getSubjectById(null),
                            onClick = { onEvent(TimetableUIEvent.ChangeDialogState(true)) }
                        )
                    }
            }
        }

    }
}