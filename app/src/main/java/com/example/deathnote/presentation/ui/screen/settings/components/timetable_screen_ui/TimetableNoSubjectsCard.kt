package com.example.deathnote.presentation.ui.screen.settings.components.timetable_screen_ui

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.ui.theme.util.adjust
import com.example.deathnote.presentation.ui.theme.util.isDarkMode

@Composable
fun TimetableNoSubjectsCard() {
    Box(
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .fillMaxWidth()
            .heightIn(
                min = 70.dp
            )
            .clip(DeathNoteTheme.shapes.rounded12)
            .shadow(
                elevation = 4.dp,
                ambientColor = DeathNoteTheme.colors.inverseBackground
            )
            .background(
                color = DeathNoteTheme.colors.regularBackground
            )
            .animateContentSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            DeathNoteTheme.colors.tertiary.adjust(
                                if (isDarkMode()) 0.4f else 1f
                            ),
                            DeathNoteTheme.colors.regularBackground
                        )
                    )
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = stringResource(id = R.string.no_subjects).uppercase(),
                style = DeathNoteTheme.typography.settingsScreenItemSubtitle,
                color = DeathNoteTheme.colors.lightInverse
            )
        }
    }
}