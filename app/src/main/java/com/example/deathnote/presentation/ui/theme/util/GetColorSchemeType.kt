package com.example.deathnote.presentation.ui.theme.util

import com.example.deathnote.presentation.model.ColorPresentation
import com.example.deathnote.presentation.ui.theme.settings.color_schemes.UserColorScheme
import com.example.deathnote.presentation.ui.theme.settings.scheme

fun getColorSchemeType(): ColorPresentation.ColorMode = when (scheme) {
    UserColorScheme.LightOddColorScheme -> ColorPresentation.ColorMode.ODD_LIGHT
    UserColorScheme.LightEvenColorScheme -> ColorPresentation.ColorMode.EVEN_LIGHT
    UserColorScheme.DarkEvenColorScheme -> ColorPresentation.ColorMode.EVEN_DARK
    else -> ColorPresentation.ColorMode.ODD_DARK
}