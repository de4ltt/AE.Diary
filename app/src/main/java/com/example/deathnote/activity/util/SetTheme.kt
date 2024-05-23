package com.example.deathnote.activity.util

import android.content.Context
import android.content.SharedPreferences
import com.example.deathnote.presentation.model.ColorScheme

fun saveSchemePreference(context: Context, colorScheme: ColorScheme) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("Theme", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putString("Theme", colorScheme.name)
    editor.apply()
}

fun loadSchemePreference(context: Context): String? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("Theme", Context.MODE_PRIVATE)
    return sharedPreferences.getString("Theme", ColorScheme.ODD_LIGHT.name)
}