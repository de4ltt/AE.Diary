package com.example.ae_diary.presentation.ui.theme.util

import com.example.ae_diary.presentation.model.util.ColorPresentation

fun isDarkMode(): Boolean =
    getColorSchemeType() == ColorPresentation.ColorMode.EVEN_DARK || getColorSchemeType() == ColorPresentation.ColorMode.ODD_DARK