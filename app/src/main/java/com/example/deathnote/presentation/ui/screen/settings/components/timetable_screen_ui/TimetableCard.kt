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
import com.example.deathnote.presentation.model.TimetableOld
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun TimetableCard(
    timetable: TimetableOld
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
            text = stringResource(id = timetable.dayOfWeek.title).uppercase(),
            style = DeathNoteTheme.typography.timeTableCard,
            color = DeathNoteTheme.colors.inverse
        )

        Column(
            modifier = Modifier
                .wrapContentSize(),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            TimeTableSubjectCard(subjectScheduled = timetable.daySubjects.subject1)
            TimeTableSubjectCard(subjectScheduled = timetable.daySubjects.subject2)
            TimeTableSubjectCard(subjectScheduled = timetable.daySubjects.subject3)
            TimeTableSubjectCard(subjectScheduled = timetable.daySubjects.subject4)
        }
    }
}