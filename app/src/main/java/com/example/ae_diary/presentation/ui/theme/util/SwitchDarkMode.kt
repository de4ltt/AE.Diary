package com.example.ae_diary.presentation.ui.theme.util

import android.content.Context
import com.example.ae_diary.activity.MainActivity
import com.example.ae_diary.activity.util.changeScheme
import com.example.ae_diary.presentation.model.util.ColorPresentation
import com.example.ae_diary.presentation.ui.theme.settings.color_schemes.DefaultColorScheme
import com.example.ae_diary.presentation.ui.theme.settings.scheme

fun switchDarkMode(
    context: Context
) {
    scheme = when (getColorSchemeType()) {
        ColorPresentation.ColorMode.EVEN_LIGHT -> DefaultColorScheme.DarkEvenColorScheme
        ColorPresentation.ColorMode.EVEN_DARK -> DefaultColorScheme.LightEvenColorScheme
        ColorPresentation.ColorMode.ODD_LIGHT -> DefaultColorScheme.DarkOddColorScheme
        ColorPresentation.ColorMode.ODD_DARK -> DefaultColorScheme.LightOddColorScheme
    }
    changeScheme(getColorSchemeType(), context as MainActivity)
}