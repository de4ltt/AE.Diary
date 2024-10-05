package com.example.ae_diary.presentation.ui.common

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.awaitFirstDown
import androidx.compose.foundation.gestures.waitForUpOrCancellation
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.scale
import androidx.compose.ui.input.pointer.pointerInput

private enum class ButtonState { Pressed, Idle }

@SuppressLint("ReturnFromAwaitPointerEventScope")
fun Modifier.bounceClick(
    scale : Float = 0.97f
) = composed {

    var buttonState by remember { mutableStateOf(ButtonState.Idle) }

    val scalingValue by animateFloatAsState(
        if (buttonState == ButtonState.Pressed) scale else 1f,
        label = ""
    )

    this
        .scale(scalingValue)
        .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = {  }
        )
        .pointerInput(buttonState) {
            awaitPointerEventScope {
                buttonState = if (buttonState == ButtonState.Pressed) {
                    waitForUpOrCancellation()
                    ButtonState.Idle
                } else {
                    awaitFirstDown(false)
                    ButtonState.Pressed
                }
            }
        }
}