package com.example.ae_diary.presentation.ui.theme.util

import com.example.ae_diary.presentation.model.util.ColorPresentation
import com.example.ae_diary.presentation.ui.theme.settings.color_schemes.DefaultColorScheme
import com.example.ae_diary.presentation.ui.theme.settings.scheme

fun setColorScheme(
    colorMode: ColorPresentation.ColorMode
) {
    scheme = when (colorMode) {
        ColorPresentation.ColorMode.EVEN_LIGHT -> DefaultColorScheme.LightEvenColorScheme
        ColorPresentation.ColorMode.EVEN_DARK -> DefaultColorScheme.DarkEvenColorScheme
        ColorPresentation.ColorMode.ODD_LIGHT -> DefaultColorScheme.LightOddColorScheme
        ColorPresentation.ColorMode.ODD_DARK -> DefaultColorScheme.DarkOddColorScheme
    }
}