package com.example.deathnote.presentation.ui.screen.main_screen.components.diary_screen_ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun ChangeSubject(
    paddingValues: PaddingValues,
    value: String,
    onRightArrowClick: () -> Unit,
    onLeftArrowClick: () -> Unit,
    onSubjectClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(paddingValues),
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
                    onClick = onLeftArrowClick
                ),
            tint = DeathNoteTheme.colors.lightInverse
        )

        Text(
            text = value,
            modifier = Modifier
                .weight(1f)
                .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onSubjectClick
            ),
            style = DeathNoteTheme.typography.subjectCardTimetableTitle,
            color = DeathNoteTheme.colors.lightInverse,
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
                    onClick = onRightArrowClick
                ),
            tint = DeathNoteTheme.colors.lightInverse
        )
    }
}