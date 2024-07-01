package com.example.deathnote.presentation.ui.screen.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.ui.screen.settings.components.main_screen_ui.CurrentDate
import com.example.deathnote.presentation.ui.screen.settings.components.main_screen_ui.CurrentSubject
import com.example.deathnote.presentation.ui.screen.settings.components.main_screen_ui.ProgressBar
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.ramcosta.composedestinations.annotation.Destination


@Destination
@Composable
fun MainScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = DeathNoteTheme.colors.regular
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(
                    shape = DeathNoteTheme.shapes.rounded_bot_strt
                )
                .background(
                    color = DeathNoteTheme.colors.inverseBackground
                ),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            CurrentDate()
            CurrentSubject()
            ProgressBar()
            Spacer(modifier = Modifier.height(300.dp))
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DeathNoteTheme.colors.inverseBackground)
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = DeathNoteTheme.shapes.rounded_top_end)
                    .background(color = DeathNoteTheme.colors.regular),
                columns = GridCells.Fixed(2)
            ) {

            }
        }
    }
}