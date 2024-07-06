package com.example.deathnote.presentation.ui.screen.settings.components.timetable_screen_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.model.DayOfWeek
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.util.toFormatDayOfWeek
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import kotlin.reflect.KFunction1

@Composable
fun TimetableCard(
    dayOfWeek: String,
    timetable: List<Timetable>,
    getSubjectById: KFunction1<Int?, Subject?>
) {
    Column(
        modifier = Modifier
            .padding(vertical = 20.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            textAlign = TextAlign.Center,
            text = stringResource(id = dayOfWeek.toFormatDayOfWeek()).uppercase(),
            style = DeathNoteTheme.typography.timeTableCard,
            color = DeathNoteTheme.colors.inverse
        )

        Column(
            modifier = Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TimeTableSubjectCard(subjectScheduled = getSubjectById(timetable[0].subjectId))
            TimeTableSubjectCard(subjectScheduled = getSubjectById(timetable[1].subjectId))
            TimeTableSubjectCard(subjectScheduled = getSubjectById(timetable[2].subjectId))
            TimeTableSubjectCard(subjectScheduled = getSubjectById(timetable[3].subjectId))
        }
    }
}