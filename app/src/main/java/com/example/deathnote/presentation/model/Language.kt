package com.example.deathnote.presentation.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.example.deathnote.R

enum class Language(
    @StringRes val title: Int,
    val code: String,
    @DrawableRes val icon: Int,
    val dominantColors: List<Color>
) {

    ENGLISH(
        R.string.english, "en", R.drawable.uk,
        listOf(
            Color(255,255,255, 255),
            Color(255, 186, 181, 255),
            Color(186, 210, 255, 255)
        )
    ),
    RUSSIAN(
        R.string.russian, "ru", R.drawable.russia,
        listOf(
            Color(255,255,255, 255),
            Color(186, 210, 255, 255),
            Color(255, 186, 181, 255)
        )
    ),
    UKRAINIAN(
        R.string.ukrainian, "uk", R.drawable.ukraine,
        listOf(
            Color(255,255,255, 255),
            Color(187, 221, 255, 255),
            Color(255, 243, 187, 255)
        )
    )

}