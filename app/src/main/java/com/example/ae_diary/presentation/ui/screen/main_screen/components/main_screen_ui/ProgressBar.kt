package com.example.ae_diary.presentation.ui.screen.main_screen.components.main_screen_ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.ae_diary.presentation.model.state.MainScreenUIState
import com.example.ae_diary.presentation.ui.theme.LightGray
import com.example.ae_diary.presentation.ui.theme.SemiLightGray
import com.example.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun ProgressBar(
    state: MainScreenUIState
) {
    state.apply {

        val curProgress by animateFloatAsState(percentage, label = "progress")

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(7.dp)
                .clip(DeathNoteTheme.shapes.rounded12)
                .background(SemiLightGray)
                .animateContentSize()
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = curProgress)
                    .background(LightGray)
            )
        }

    }
}