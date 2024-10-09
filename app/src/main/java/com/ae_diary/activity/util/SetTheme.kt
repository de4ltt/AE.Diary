package com.ae_diary.activity.util

import android.content.Context
import android.content.SharedPreferences
import com.ae_diary.presentation.model.util.ColorPresentation

fun saveSchemePreference(context: Context, colorMode: ColorPresentation.ColorMode) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("Theme", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putString("Theme", colorMode.name)
    editor.apply()
}

fun loadSchemePreference(context: Context): String? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("Theme", Context.MODE_PRIVATE)
    return sharedPreferences.getString("Theme", ColorPresentation.ColorMode.ODD_LIGHT.name)
}