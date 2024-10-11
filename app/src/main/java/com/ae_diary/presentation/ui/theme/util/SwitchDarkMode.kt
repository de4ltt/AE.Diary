package com.ae_diary.presentation.ui.theme.util

import android.content.Context
import com.ae_diary.activity.MainActivity
import com.ae_diary.activity.util.changeScheme
import com.ae_diary.presentation.model.util.ColorPresentation
import com.ae_diary.presentation.ui.theme.settings.color_schemes.DefaultColorScheme
import com.ae_diary.presentation.ui.theme.settings.scheme

fun switchDarkMode(context: Context) {

    scheme = when (getColorSchemeType()) {
        ColorPresentation.ColorMode.EVEN_LIGHT -> DefaultColorScheme.DarkEvenColorScheme
        ColorPresentation.ColorMode.EVEN_DARK -> DefaultColorScheme.LightEvenColorScheme
        ColorPresentation.ColorMode.ODD_LIGHT -> DefaultColorScheme.DarkOddColorScheme
        ColorPresentation.ColorMode.ODD_DARK -> DefaultColorScheme.LightOddColorScheme
    }
    changeScheme(getColorSchemeType(), context as MainActivity)
}