package com.ae_diary.presentation.ui.theme

import androidx.compose.runtime.Composable
import com.ae_diary.presentation.ui.theme.settings.ExtendedTheme
import com.ae_diary.presentation.ui.theme.settings.scheme

@Composable
fun DeathNoteTheme(
    content: @Composable () -> Unit
) {
    ExtendedTheme(
        colors = scheme,
        content = content
    )
}