package com.example.deathnote.presentation.ui.theme.util

import android.content.Context
import com.example.deathnote.activity.MainActivity
import com.example.deathnote.presentation.model.util.ColorPresentation
import com.example.deathnote.presentation.ui.theme.settings.color_schemes.DefaultColorScheme
import com.example.deathnote.presentation.ui.theme.settings.scheme

fun switchDarkMode(
    context: Context
) {
    scheme = when (getColorSchemeType()) {
        ColorPresentation.ColorMode.EVEN_LIGHT -> DefaultColorScheme.DarkEvenColorScheme
        ColorPresentation.ColorMode.EVEN_DARK -> DefaultColorScheme.LightEvenColorScheme
        ColorPresentation.ColorMode.ODD_LIGHT -> DefaultColorScheme.DarkOddColorScheme
        ColorPresentation.ColorMode.ODD_DARK -> DefaultColorScheme.LightOddColorScheme
    }
    (context as MainActivity).changeScheme(getColorSchemeType())
}