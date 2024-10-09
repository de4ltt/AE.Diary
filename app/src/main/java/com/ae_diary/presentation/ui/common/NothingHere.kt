package com.ae_diary.presentation.ui.common

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.ae_diary.R
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun NothingHere(
    modifier: Modifier = Modifier,
    targetState: Boolean = true,
    content: @Composable () -> Unit
) {

    val transitionTime = 500

    AnimatedContent(
        targetState = !targetState,
        modifier = modifier.fillMaxSize(),
        transitionSpec = {
            (fadeIn(tween(transitionTime))).togetherWith(
                (fadeOut(tween(transitionTime)))
            )
        },
        label = ""
    ) {
        if (it) {
            Box(
                modifier = modifier,
                content = { content() }
            )
        } else
            Box(
                modifier = modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = stringResource(id = R.string.nothing_here),
                    color = DeathNoteTheme.colors.lightInverse,
                    fontSize = 18.sp
                )
            }
    }
}