package com.example.ae_diary.presentation.ui.theme

import androidx.compose.runtime.Composable
import com.example.ae_diary.presentation.ui.theme.settings.ExtendedTheme
import com.example.ae_diary.presentation.ui.theme.settings.scheme

@Composable
fun DeathNoteTheme(
    content: @Composable () -> Unit
) {
    ExtendedTheme(
        colors = scheme,
        content = content
    )
}