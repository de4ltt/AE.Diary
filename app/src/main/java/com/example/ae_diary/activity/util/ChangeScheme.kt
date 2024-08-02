package com.example.ae_diary.activity.util

import android.content.Context
import com.example.ae_diary.presentation.model.util.ColorPresentation

fun changeScheme(colorMode: ColorPresentation.ColorMode, context: Context) =
    saveSchemePreference(context, colorMode)