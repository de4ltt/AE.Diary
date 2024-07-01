package com.example.deathnote.presentation.ui.theme.util

import com.example.deathnote.presentation.model.ColorPresentation
import com.example.deathnote.presentation.ui.theme.settings.color_schemes.DefaultColorScheme
import com.example.deathnote.presentation.ui.theme.settings.scheme

fun getColorSchemeType(): ColorPresentation.ColorMode = when (scheme) {
    DefaultColorScheme.LightOddColorScheme -> ColorPresentation.ColorMode.ODD_LIGHT
    DefaultColorScheme.LightEvenColorScheme -> ColorPresentation.ColorMode.EVEN_LIGHT
    DefaultColorScheme.DarkEvenColorScheme -> ColorPresentation.ColorMode.EVEN_DARK
    else -> ColorPresentation.ColorMode.ODD_DARK
}