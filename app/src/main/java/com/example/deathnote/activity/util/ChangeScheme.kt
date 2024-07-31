package com.example.deathnote.activity.util

import android.content.Context
import com.example.deathnote.presentation.model.util.ColorPresentation

fun changeScheme(colorMode: ColorPresentation.ColorMode, context: Context) =
    saveSchemePreference(context, colorMode)