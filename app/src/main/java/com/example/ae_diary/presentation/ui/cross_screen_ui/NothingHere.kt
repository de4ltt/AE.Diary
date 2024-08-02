package com.example.ae_diary.presentation.ui.cross_screen_ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import com.example.ae_diary.R
import com.example.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun NothingHere() {

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = stringResource(id = R.string.nothing_here),
            color = DeathNoteTheme.colors.lightInverse,
            fontSize = 18.sp
        )
    }
}