package com.example.deathnote.presentation.ui.screen.settings.components.timetable_screen_ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.ui.cross_screen_ui.delete_container.SwipeToDeleteContainer
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.ui.theme.util.adjust
import com.example.deathnote.presentation.ui.theme.util.isDarkMode

@Composable
fun TimeTableSubjectCard(
    onClick: () -> Unit = {},
    onDelete: () -> Unit = {},
    classTime: Pair<String?, String?>,
    subjectScheduled: Subject?
) {

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
            .animateContentSize(
                animationSpec = tween(25)
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
    ) {
        if (subjectScheduled != null) {
            subjectScheduled.apply {
                SwipeToDeleteContainer(
                    item = subjectScheduled,
                    onDelete = onDelete
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(
                                color = DeathNoteTheme.colors.regularBackground
                            )
                            .padding(
                                top = 8.dp,
                                start = 15.dp,
                                end = 15.dp,
                                bottom = 10.dp
                            )
                    ) {
                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        color = if (type == "lk")
                                            DeathNoteTheme.colors.secondary.adjust(
                                                if (isDarkMode()) 1.8f else 1f
                                            )
                                        else DeathNoteTheme.colors.primaryDefault.adjust(
                                            if (isDarkMode()) 1.4f else 1f
                                        )
                                    )
                                ) {
                                    append(
                                        if (type == "lk")
                                            stringResource(id = R.string.lecture)
                                        else stringResource(id = R.string.practice)
                                    )
                                }

                                withStyle(
                                    style = SpanStyle(
                                        color = DeathNoteTheme.colors.inverse
                                    )
                                ) {
                                    append(
                                        " // " + classTime.first + " - " + classTime.second
                                    )
                                }
                            },
                            fontSize = DeathNoteTheme.typography.settingsScreenItemSubtitle.fontSize,
                            lineHeight = 13.sp
                        )

                        Text(
                            modifier = Modifier.padding(top = 7.dp, bottom = 3.dp),
                            text = subjectScheduled.name,
                            style = DeathNoteTheme.typography.subjectCardTimetableTitle,
                            color = DeathNoteTheme.colors.inverse
                        )
                    }
                }
            }
        } else {
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
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = "plus",
                    modifier = Modifier.size(25.dp),
                    tint = DeathNoteTheme.colors.inverse
                )
            }
        }
    }
}