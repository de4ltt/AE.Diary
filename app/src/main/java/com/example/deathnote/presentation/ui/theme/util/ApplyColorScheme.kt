package com.example.deathnote.presentation.ui.theme.util

import com.example.deathnote.presentation.model.ColorPresentation
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteColors
import com.example.deathnote.presentation.ui.theme.settings.color_schemes.DefaultColorScheme

fun applyColorScheme(
    colors: DeathNoteColors,
    scheme: ColorPresentation.ColorMode
) {
    when (scheme) {
        ColorPresentation.ColorMode.EVEN_LIGHT -> DefaultColorScheme.LightEvenColorScheme
        ColorPresentation.ColorMode.ODD_LIGHT -> DefaultColorScheme.LightOddColorScheme
        ColorPresentation.ColorMode.EVEN_DARK -> DefaultColorScheme.DarkEvenColorScheme
        ColorPresentation.ColorMode.ODD_DARK -> DefaultColorScheme.DarkOddColorScheme
    }
}