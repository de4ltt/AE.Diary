package com.example.deathnote.presentation.ui.theme.util

import com.example.deathnote.presentation.model.ColorPresentation
import com.example.deathnote.presentation.ui.theme.settings.color_schemes.UserColorScheme
import com.example.deathnote.presentation.ui.theme.settings.scheme

fun setColorScheme(
    colorMode: ColorPresentation.ColorMode
) {
    scheme = when (colorMode) {
        ColorPresentation.ColorMode.EVEN_LIGHT -> UserColorScheme.LightEvenColorScheme
        ColorPresentation.ColorMode.EVEN_DARK -> UserColorScheme.DarkEvenColorScheme
        ColorPresentation.ColorMode.ODD_LIGHT -> UserColorScheme.LightOddColorScheme
        ColorPresentation.ColorMode.ODD_DARK -> UserColorScheme.DarkOddColorScheme
    }
}