package com.example.deathnote.presentation.ui.screen.settings.components.students_screen_ui

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.util.getShortName
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun StudentBar(
    index: Int,
    student: Student
) {

    var isDeleteMode by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .wrapContentSize()
            .shadow(
                elevation = 4.dp,
                shape = DeathNoteTheme.shapes.rounded12,
                ambientColor = DeathNoteTheme.colors.regularBackground
            )
            .background(DeathNoteTheme.colors.regularBackground)
    ) {
        Crossfade(
            targetState = isDeleteMode,
            label = "bar",
            animationSpec = tween(
                durationMillis = 220,
                easing = LinearEasing
            )
        ) {
            when {
                it -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(
                                min = 60.dp,
                                max = 80.dp
                            )
                            .wrapContentHeight()
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(28.dp)
                                .background(
                                    color = DeathNoteTheme.colors.secondary
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.church),
                                contentDescription = "church",
                                tint = DeathNoteTheme.colors.regular,
                                modifier = Modifier.size(18.dp)
                            )
                        }


                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = DeathNoteTheme.colors.secondaryBackground
                                )
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.weight(1f).padding(end = 10.dp),
                                text = "${stringResource(id = R.string.to_delete)}\n${student.getShortName()}",
                                style = DeathNoteTheme.typography.itemCardTitle,
                                color = DeathNoteTheme.colors.regular
                            )

                            Row(
                                modifier = Modifier
                                    .wrapContentWidth()
                                    .fillMaxHeight(),
                                horizontalArrangement = Arrangement.spacedBy(6.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    painter = painterResource(id = R.drawable.disagree),
                                    contentDescription = "disagree",
                                    tint = DeathNoteTheme.colors.regular,
                                    modifier = Modifier
                                        .size(30.dp)
                                        .pointerInput(Unit) {
                                            detectTapGestures {
                                                isDeleteMode = false
                                            }
                                        }
                                )

                                Icon(
                                    painter = painterResource(id = R.drawable.agree),
                                    contentDescription = "agree",
                                    tint = DeathNoteTheme.colors.regular,
                                    modifier = Modifier
                                        .size(23.dp)
                                        .pointerInput(Unit) {
                                            detectTapGestures {
                                                //TODO
                                            }
                                        }
                                )
                            }
                        }
                    }
                }

                else -> {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(
                                min = 60.dp,
                                max = 80.dp
                            )
                            .wrapContentHeight()
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        isDeleteMode = true
                                    }
                                )
                            }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(28.dp)
                                .background(
                                    color = DeathNoteTheme.colors.primary,
                                    shape = DeathNoteTheme.shapes.rounded12_left
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$index",
                                style = DeathNoteTheme.typography.itemCardIndex,
                                color = DeathNoteTheme.colors.regular
                            )
                        }


                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = DeathNoteTheme.colors.regularBackground
                                )
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.weight(1f).padding(end = 10.dp),
                                text = "${student.surname}\n${student.name} ${student.patronymic ?: ""}",
                                style = DeathNoteTheme.typography.itemCardTitle,
                                color = DeathNoteTheme.colors.inverse
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.pencil),
                                contentDescription = "edit_pencil",
                                tint = DeathNoteTheme.colors.primary,
                                modifier = Modifier
                                    .pointerInput(Unit) {
                                        detectTapGestures {
                                            //TODO
                                        }
                                    }
                            )
                        }
                    }
                }
            }
        }
    }
}

