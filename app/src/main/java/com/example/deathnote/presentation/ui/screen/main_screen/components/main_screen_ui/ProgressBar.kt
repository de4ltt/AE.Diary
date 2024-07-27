package com.example.deathnote.presentation.ui.screen.main_screen.components.main_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.model.state.MainScreenUIState
import com.example.deathnote.presentation.ui.theme.LightGray
import com.example.deathnote.presentation.ui.theme.SemiLightGray
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun ProgressBar(
    state: MainScreenUIState
) {
    state.apply {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(7.dp)
                .clip(DeathNoteTheme.shapes.rounded12)
                .background(SemiLightGray)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(fraction = percentage)
                    .background(LightGray)
            )
        }

    }
}