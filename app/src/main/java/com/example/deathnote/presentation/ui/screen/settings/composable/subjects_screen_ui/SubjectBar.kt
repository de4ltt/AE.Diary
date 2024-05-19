package com.example.deathnote.presentation.ui.screen.settings.composable.subjects_screen_ui

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.SubjectType
import com.example.deathnote.presentation.model.getShortName

@Composable
fun SubjectBar(
    index: Int,
    subject: Subject,
    roundedCornerShape: RoundedCornerShape = RoundedCornerShape(12.dp)
) {

    var isDeleteMode by remember {
        mutableStateOf(false)
    }

    Row(
        modifier = Modifier
            .wrapContentSize()
            .shadow(
                elevation = 4.dp,
                shape = roundedCornerShape,
                ambientColor = MaterialTheme.colorScheme.surfaceTint
            )
            .background(MaterialTheme.colorScheme.surfaceTint)
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
                            .pointerInput(Unit) {
                                detectTapGestures(
                                    onLongPress = {
                                        isDeleteMode = true
                                    }
                                )
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(28.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.secondary
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(
                                painter = painterResource(id = R.drawable.church),
                                contentDescription = "church",
                                tint = MaterialTheme.colorScheme.inversePrimary,
                                modifier = Modifier.size(18.dp)
                            )
                        }


                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = MaterialTheme.colorScheme.secondaryContainer
                                )
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(0.7f),
                                text = "${stringResource(id = R.string.to_delete)}\n${subject.getShortName()} (${
                                    if (subject.subjectType == SubjectType.LECTURE) stringResource(
                                        id = R.string.lk
                                    )
                                    else stringResource(id = R.string.pr)
                                })",
                                fontSize = 16.sp,
                                lineHeight = 18.sp,
                                fontFamily = FontFamily(Font(R.font.inter_regular)),
                                color = MaterialTheme.colorScheme.inversePrimary
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
                                    tint = MaterialTheme.colorScheme.inversePrimary,
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
                                    tint = MaterialTheme.colorScheme.inversePrimary,
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
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxHeight()
                                .width(28.dp)
                                .background(
                                    color = MaterialTheme.colorScheme.primary
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "$index",
                                fontSize = 18.sp,
                                fontFamily = FontFamily(Font(R.font.inter_bold)),
                                color = MaterialTheme.colorScheme.inversePrimary
                            )
                        }


                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(
                                    color = MaterialTheme.colorScheme.inversePrimary
                                )
                                .padding(horizontal = 15.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                modifier = Modifier.fillMaxWidth(0.7f),
                                text = "${subject.name} (${
                                    if (subject.subjectType == SubjectType.LECTURE) stringResource(
                                        id = R.string.lk
                                    )
                                    else stringResource(id = R.string.pr)
                                })",
                                fontSize = 16.sp,
                                lineHeight = 18.sp,
                                fontFamily = FontFamily(Font(R.font.inter_regular)),
                                color = MaterialTheme.colorScheme.onPrimary
                            )

                            Icon(
                                painter = painterResource(id = R.drawable.pencil),
                                contentDescription = "edit_pencil",
                                tint = MaterialTheme.colorScheme.primary,
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

