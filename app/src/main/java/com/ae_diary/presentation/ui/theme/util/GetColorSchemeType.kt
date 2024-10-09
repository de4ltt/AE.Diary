package com.ae_diary.presentation.ui.theme.util

import com.ae_diary.presentation.model.util.ColorPresentation
import com.ae_diary.presentation.ui.theme.settings.color_schemes.DefaultColorScheme
import com.ae_diary.presentation.ui.theme.settings.scheme

fun getColorSchemeType(): ColorPresentation.ColorMode = when (scheme) {
    DefaultColorScheme.LightOddColorScheme -> ColorPresentation.ColorMode.ODD_LIGHT
    DefaultColorScheme.LightEvenColorScheme -> ColorPresentation.ColorMode.EVEN_LIGHT
    DefaultColorScheme.DarkEvenColorScheme -> ColorPresentation.ColorMode.EVEN_DARK
    else -> ColorPresentation.ColorMode.ODD_DARK
}