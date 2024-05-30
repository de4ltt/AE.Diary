package com.example.deathnote.presentation.ui.theme.util

import com.example.deathnote.presentation.model.ColorPresentation

fun isEvenWeek(): Boolean =
    getColorSchemeType() == ColorPresentation.ColorMode.EVEN_LIGHT
            || getColorSchemeType() == ColorPresentation.ColorMode.EVEN_DARK