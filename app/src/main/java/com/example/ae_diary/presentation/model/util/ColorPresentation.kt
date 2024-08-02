package com.example.ae_diary.presentation.model.util

sealed class ColorPresentation {
    enum class ColorMode(
        scheme: String
    ) {

        EVEN_LIGHT("even_light"),
        EVEN_DARK("even_dark"),
        ODD_LIGHT("odd_light"),
        ODD_DARK("odd_dark")
    }

    enum class ColorType {
        PRIMARY,
        PRIMARY_DEFAULT,
        PRIMARY_BACKGROUND,
        SECONDARY,
        SECONDARY_BACKGROUND,
        TERTIARY,
        REGULAR,
        REGULAR_BACKGROUND,
        INVERSE,
        INVERSE_BACKGROUND,
        LIGHT_INVERSE,
        BASE_BACKGROUND
    }
}