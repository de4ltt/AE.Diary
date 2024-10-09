package com.ae_diary.presentation.ui.common

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.Crossfade
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import com.ae_diary.presentation.ui.theme.Black

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedText(
    modifier: Modifier = Modifier,
    textStyle: TextStyle = TextStyle.Default,
    color: Color = Black,
    sharedTransitionScope: SharedTransitionScope,
    animatedVisibilityScope: AnimatedVisibilityScope,
    @StringRes stringRes: Int
) {

    Crossfade(targetState = stringRes, label = "") {

        with(sharedTransitionScope) {

            val sharedContentState = rememberSharedContentState(key = "content")

            val animationDuration = 500
            val enterAnimation = fadeIn(tween(animationDuration))
            val exitAnimation = fadeOut(tween(0))

            Text(
                text = stringResource(id = it),
                modifier = modifier
                    .sharedBounds(
                        sharedContentState = sharedContentState,
                        animatedVisibilityScope = animatedVisibilityScope,
                        enter = enterAnimation,
                        exit = exitAnimation
                    )
                    .skipToLookaheadSize(),
                style = textStyle,
                color = color
            )

        }

    }
}