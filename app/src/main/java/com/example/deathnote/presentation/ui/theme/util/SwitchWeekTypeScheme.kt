package com.example.deathnote.presentation.ui.theme.util

import android.content.Context
import com.example.deathnote.activity.MainActivity
import com.example.deathnote.presentation.model.ColorPresentation
import com.example.deathnote.presentation.ui.theme.settings.color_schemes.DefaultColorScheme
import com.example.deathnote.presentation.ui.theme.settings.scheme

fun switchWeekTypeScheme(
    context: Context
) {
    scheme = when (getColorSchemeType()) {
        ColorPresentation.ColorMode.EVEN_LIGHT -> DefaultColorScheme.LightOddColorScheme
        ColorPresentation.ColorMode.EVEN_DARK -> DefaultColorScheme.DarkOddColorScheme
        ColorPresentation.ColorMode.ODD_LIGHT -> DefaultColorScheme.LightEvenColorScheme
        ColorPresentation.ColorMode.ODD_DARK -> DefaultColorScheme.DarkEvenColorScheme
    }
    (context as MainActivity).changeScheme(getColorSchemeType())
}