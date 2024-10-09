package com.ae_diary.presentation.model.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.graphics.Color
import com.ae_diary.R

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
    JAPANESE(
        R.string.japanese, "ja",R.drawable.japan,
        listOf(
            Color(255,255,255, 255),
            Color(255, 180, 180, 255),
            Color(255, 255, 255, 255)
        )
    ),
    KAZAKH(
        R.string.kazakh, "kk",R.drawable.kazakhstan,
        listOf(
            Color(255,255,255, 255),
            Color(255, 243, 187, 255),
            Color(157, 227, 255, 255)
        )
    ),
    POLISH(
        R.string.polish, "pl",R.drawable.poland,
        listOf(
            Color(255,255,255, 255),
            Color(255, 232, 232, 255),
            Color(255, 185, 185, 255)
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