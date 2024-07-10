package com.example.deathnote.presentation.ui.screen.main_screen.components.diary_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Absence
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.util.getShortName

@Composable
fun StudentCard(
    student: Student,
    isAbsent: Boolean = false,
    isAbsRes: Boolean = false,
    onEvent: (DiaryUIEvent) -> Unit = {},
    titled: Boolean = false
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(
            modifier = Modifier
                .height(50.dp)
                .weight(1f)
                .clip(
                    shape = DeathNoteTheme.shapes.rounded12
                )
                .background(DeathNoteTheme.colors.regularBackground),
            contentAlignment = Alignment.Center
        ) {
           Text(
               text = student.getShortName().uppercase(),
               style = DeathNoteTheme.typography.settingsScreenItemTitle,
               color = DeathNoteTheme.colors.inverse,
               fontStyle = FontStyle.Normal
           )
        }

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(
                    shape = DeathNoteTheme.shapes.rounded12
                )
                .background(DeathNoteTheme.colors.regularBackground),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isAbsRes) stringResource(id = R.string.absent_respectful) else "",
                style = DeathNoteTheme.typography.settingsScreenItemTitle,
                color = DeathNoteTheme.colors.inverse,
                fontStyle = FontStyle.Normal
            )
        }

        Box(
            modifier = Modifier
                .size(50.dp)
                .clip(
                    shape = DeathNoteTheme.shapes.rounded12
                )
                .background(DeathNoteTheme.colors.regularBackground),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (isAbsent) stringResource(id = R.string.absent) else ,
                style = DeathNoteTheme.typography.settingsScreenItemTitle,
                color = DeathNoteTheme.colors.inverse,
                fontStyle = FontStyle.Normal
            )
        }
    }
}