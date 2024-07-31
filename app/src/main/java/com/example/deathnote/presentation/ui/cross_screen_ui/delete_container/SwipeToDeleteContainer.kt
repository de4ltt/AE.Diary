package com.example.deathnote.presentation.ui.cross_screen_ui.delete_container

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DismissDirection
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.DismissValue
import androidx.compose.material.ExperimentalMaterialApi
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.SwipeToDismiss
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.rememberDismissState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun <T> SwipeToDeleteContainer(
    item: T?,
    onDelete: () -> Unit,
    animationDuration: Int = 500,
    content: @Composable (T) -> Unit
) {

    if (item != null) {
        var isRemoved by remember {
            mutableStateOf(false)
        }
        val state = rememberDismissState(
            confirmStateChange = { value ->
                if (value == DismissValue.DismissedToStart) {
                    isRemoved = true
                    true
                } else {
                    false
                }
            }
        )

        LaunchedEffect(key1 = isRemoved) {
            if (isRemoved) {
                delay(animationDuration.toLong())
                onDelete()
            }
        }

        AnimatedVisibility(
            visible = !isRemoved,
            exit = shrinkVertically(
                animationSpec = tween(durationMillis = animationDuration),
                shrinkTowards = Alignment.Top
            ) + fadeOut()
        ) {
            SwipeToDismiss(
                state = state,
                background = {
                    DeleteBackground(swipeDismissState = state)
                },
                dismissContent = { content(item) },
                directions = setOf(DismissDirection.EndToStart)
            )
        }
    }
}