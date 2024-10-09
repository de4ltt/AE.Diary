package com.ae_diary.presentation.ui.common

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource

@Composable
fun AnimatedVisibilityIcon(
    @DrawableRes painterId: Int?,
    contentDescription: String?,
    modifier: Modifier = Modifier,
    tint: Color,
    targetState: Boolean
) {

    val animationDuration = 500
    val transitionSpec =
        (fadeIn(tween(animationDuration)) + slideInHorizontally(tween(animationDuration)) { it * 2 }).togetherWith(
            fadeOut(tween(animationDuration - 400))
        )

    AnimatedContent(
        targetState = targetState,
        label = contentDescription ?: "",
        transitionSpec = { transitionSpec }
    ) {
        if (it)
            painterId?.let { id ->
                Icon(
                    painter = painterResource(id = id),
                    contentDescription = contentDescription ?: "",
                    modifier = modifier,
                    tint = tint
                )
            }
    }
}