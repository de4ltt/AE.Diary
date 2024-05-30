package com.example.deathnote.presentation.ui.theme.util

import com.example.deathnote.presentation.model.ColorPresentation
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteColors
import com.example.deathnote.presentation.ui.theme.settings.color_schemes.UserColorScheme

fun applyColorScheme(
    colors: DeathNoteColors,
    scheme: ColorPresentation.ColorMode
) {
    when (scheme) {
        ColorPresentation.ColorMode.EVEN_LIGHT -> UserColorScheme.LightEvenColorScheme = colors
        ColorPresentation.ColorMode.ODD_LIGHT -> UserColorScheme.LightOddColorScheme = colors
        ColorPresentation.ColorMode.EVEN_DARK -> UserColorScheme.DarkEvenColorScheme
        ColorPresentation.ColorMode.ODD_DARK -> UserColorScheme.DarkOddColorScheme
    }
}