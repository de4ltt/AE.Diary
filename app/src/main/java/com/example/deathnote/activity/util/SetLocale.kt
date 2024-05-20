package com.example.deathnote.activity.util

import android.content.Context
import android.content.SharedPreferences
import android.content.res.Configuration
import java.util.Locale

fun setLocale(context: Context, language: String) {
    val locale = Locale(language)
    Locale.setDefault(locale)
    val config = Configuration()
    config.setLocale(locale)
    context.resources.updateConfiguration(config, context.resources.displayMetrics)
    saveLanguagePreference(context, language)
}

fun saveLanguagePreference(context: Context, language: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("Language", Context.MODE_PRIVATE)
    val editor: SharedPreferences.Editor = sharedPreferences.edit()
    editor.putString("Language", language)
    editor.apply()
}

fun loadLanguagePreference(context: Context): String? {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("Language", Context.MODE_PRIVATE)
    return sharedPreferences.getString("Language", "en")
}