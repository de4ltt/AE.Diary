package com.ae_diary.presentation.ui.theme.util

import com.ae_diary.presentation.model.util.ColorPresentation

fun isEvenWeek(): Boolean =
            getColorSchemeType() == ColorPresentation.ColorMode.EVEN_LIGHT ||
            getColorSchemeType() == ColorPresentation.ColorMode.EVEN_DARK