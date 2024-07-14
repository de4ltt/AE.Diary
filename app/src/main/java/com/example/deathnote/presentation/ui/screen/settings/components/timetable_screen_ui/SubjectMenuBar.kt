package com.example.deathnote.presentation.ui.screen.settings.components.timetable_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.ui.theme.DarkRed
import com.example.deathnote.presentation.ui.theme.DarkYellow
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun SubjectMenuBar(
    subject: Subject,
    onSelect: (Subject) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(
                min = 60.dp,
                max = 80.dp
            )
            .wrapContentHeight()
            .shadow(
                elevation = 4.dp,
                shape = DeathNoteTheme.shapes.rounded12,
                ambientColor = DeathNoteTheme.colors.regularBackground
            )
            .background(DeathNoteTheme.colors.regularBackground)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onSelect(subject) }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(40.dp)
                .background(
                    color = if (subject.type == "lk") DarkRed else DarkYellow
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = if (subject.type == "lk" || subject.type == "pr")
                    stringResource(id = if (subject.type == "lk") R.string.lk else R.string.pr).uppercase()
                else "",
                style = DeathNoteTheme.typography.itemCardIndex,
                color = DeathNoteTheme.colors.regular
            )
        }

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp),
            text = "${subject.name} (${
                if (subject.type == "lk") stringResource(
                    id = R.string.lk
                )
                else if (subject.type == "pr") stringResource(id = R.string.pr)
                else stringResource(id = R.string.all)
            })",
            style = DeathNoteTheme.typography.itemCardTitle,
            color = DeathNoteTheme.colors.inverse
        )
    }
}