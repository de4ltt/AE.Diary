package com.example.ae_diary.presentation.ui.theme.util

import android.content.Context
import com.example.ae_diary.activity.MainActivity
import com.example.ae_diary.activity.util.changeScheme
import com.example.ae_diary.presentation.model.util.ColorPresentation
import com.example.ae_diary.presentation.ui.theme.settings.color_schemes.DefaultColorScheme
import com.example.ae_diary.presentation.ui.theme.settings.scheme

fun switchWeekTypeScheme(
    context: Context
) {
    scheme = when (getColorSchemeType()) {
        ColorPresentation.ColorMode.EVEN_LIGHT -> DefaultColorScheme.LightOddColorScheme
        ColorPresentation.ColorMode.EVEN_DARK -> DefaultColorScheme.DarkOddColorScheme
        ColorPresentation.ColorMode.ODD_LIGHT -> DefaultColorScheme.LightEvenColorScheme
        ColorPresentation.ColorMode.ODD_DARK -> DefaultColorScheme.DarkEvenColorScheme
    }
    changeScheme(getColorSchemeType(), context as MainActivity)
}