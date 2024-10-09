package com.ae_diary.activity.util

import android.content.Context
import com.ae_diary.presentation.model.util.ColorPresentation

fun changeScheme(colorMode: ColorPresentation.ColorMode, context: Context) =
    saveSchemePreference(context, colorMode)