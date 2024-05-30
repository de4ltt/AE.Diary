package com.example.deathnote.presentation.ui.theme.util

import com.example.deathnote.presentation.model.ColorPresentation

fun isDarkMode(): Boolean =
    getColorSchemeType() == ColorPresentation.ColorMode.EVEN_DARK || getColorSchemeType() == ColorPresentation.ColorMode.ODD_DARK