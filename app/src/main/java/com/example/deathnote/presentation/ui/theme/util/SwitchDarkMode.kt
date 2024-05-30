package com.example.deathnote.presentation.ui.theme.util

import android.content.Context
import com.example.deathnote.activity.MainActivity
import com.example.deathnote.presentation.model.ColorPresentation
import com.example.deathnote.presentation.ui.theme.settings.color_schemes.UserColorScheme
import com.example.deathnote.presentation.ui.theme.settings.scheme

fun switchDarkMode(
    context: Context
) {
    scheme = when (getColorSchemeType()) {
        ColorPresentation.ColorMode.EVEN_LIGHT -> UserColorScheme.DarkEvenColorScheme
        ColorPresentation.ColorMode.EVEN_DARK -> UserColorScheme.LightEvenColorScheme
        ColorPresentation.ColorMode.ODD_LIGHT -> UserColorScheme.DarkOddColorScheme
        ColorPresentation.ColorMode.ODD_DARK -> UserColorScheme.LightOddColorScheme
    }
    (context as MainActivity).changeScheme(getColorSchemeType())
}