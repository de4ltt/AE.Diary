package com.ae_diary.presentation.ui.common

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ae_diary.presentation.navigation.AppDestination
import com.ae_diary.presentation.navigation.topBarIcon
import com.ae_diary.presentation.navigation.topBarLabel
import com.ae_diary.presentation.ui.theme.SexyGray
import com.ae_diary.presentation.ui.theme.SoftGray
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TopBar(
    destination: AppDestination,
    onIconClick: () -> Unit,
    isConstricted: Boolean = false,
    @StringRes onConstrictedLabelId: Int
) {

    val icon = destination.topBarIcon
    val label = destination.topBarLabel

    val columnColor by animateColorAsState(
        targetValue = DeathNoteTheme.colors.baseBackground,
        label = ""
    )
    val textColor by animateColorAsState(targetValue = DeathNoteTheme.colors.inverse, label = "")

    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(color = columnColor)
            .animateContentSize(
                animationSpec = tween(0, easing = LinearEasing)
            ),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        AnimatedVisibilityIcon(
            painterId = icon,
            contentDescription = "arrow_left",
            modifier = Modifier
                .size(40.dp)
                .pointerInput(Unit) {
                    detectTapGestures {
                        onIconClick()
                    }
                },
            tint = DeathNoteTheme.colors.inverse,
            targetState = !isConstricted
        )

        SharedTransitionLayout {

            val transitionSpec = fadeIn(tween(500)).togetherWith(fadeOut(tween(500)))

            label.let { labelId ->

                AnimatedContent(
                    targetState = isConstricted,
                    label = "constrictionImplementation",
                    transitionSpec = { transitionSpec }
                ) {

                    val textId = if (it) onConstrictedLabelId else labelId
                    val horizontalArrangement = if (it) Arrangement.Center else Arrangement.Start
                    val textStyle =
                        if (!it) DeathNoteTheme.typography.topBar else DeathNoteTheme.typography.topBar.copy(
                            fontSize = DeathNoteTheme.typography.topBar.fontSize * 0.8
                        )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = horizontalArrangement,
                        content = {
                            SharedText(
                                sharedTransitionScope = this@SharedTransitionLayout,
                                animatedVisibilityScope = this@AnimatedContent,
                                stringRes = textId,
                                textStyle = textStyle,
                                color = textColor
                            )
                        }
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun TopBar(
    destination: AppDestination,
    onIconClick: () -> Unit,
    isConstricted: Boolean = false,
) {

    val icon = destination.topBarIcon
    val label = destination.topBarLabel

    val columnColor by animateColorAsState(
        targetValue = DeathNoteTheme.colors.baseBackground,
        label = ""
    )
    val textColor by animateColorAsState(targetValue = DeathNoteTheme.colors.inverse, label = "")

    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(color = columnColor),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        AnimatedVisibilityIcon(
            painterId = icon,
            contentDescription = "arrow_left",
            modifier = Modifier
                .size(40.dp)
                .pointerInput(Unit) {
                    detectTapGestures {
                        onIconClick()
                    }
                },
            tint = DeathNoteTheme.colors.inverse,
            targetState = !isConstricted
        )

        SharedTransitionLayout {

            val transitionSpec = EnterTransition.None.togetherWith(ExitTransition.None)

            label.let { labelId ->

                AnimatedContent(
                    targetState = isConstricted,
                    label = "constrictionImplementation",
                    transitionSpec = { transitionSpec }
                ) {

                    val horizontalArrangement = if (it) Arrangement.Center else Arrangement.Start
                    val textStyle =
                        if (!it) DeathNoteTheme.typography.topBar else DeathNoteTheme.typography.topBar.copy(
                            fontSize = DeathNoteTheme.typography.topBar.fontSize * 0.8
                        )
                    val bottomPadding by animateDpAsState(
                        targetValue = if (it) 10.dp else 0.dp,
                        label = ""
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = bottomPadding),
                        horizontalArrangement = horizontalArrangement,
                        content = {

                            SharedText(
                                sharedTransitionScope = this@SharedTransitionLayout,
                                animatedVisibilityScope = this@AnimatedContent,
                                stringRes = labelId,
                                textStyle = textStyle,
                                color = textColor
                            )
                        }
                    )
                }
            }
        }

    }
}

@Composable
fun TopBar(
    destination: AppDestination,
    onIconClick: () -> Unit = {}
) {

    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(
                shape = DeathNoteTheme.shapes.rounded_bot_strt_mini
            )
            .background(
                color = SexyGray
            ), Alignment.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 60.dp,
                    bottom = 20.dp,
                    start = 25.dp,
                    end = 25.dp
                )
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            destination.topBarLabel.let {
                Text(
                    text = stringResource(id = it),
                    style = DeathNoteTheme.typography.topBar,
                    color = SoftGray
                )
            }

            destination.topBarIcon?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = "icon",
                    tint = SoftGray,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable(
                            onClick = onIconClick,
                            indication = null,
                            interactionSource = remember { MutableInteractionSource() }
                        )
                )
            }
        }
    }
}