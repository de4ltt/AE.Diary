package com.example.deathnote.presentation.ui.theme.util

import androidx.compose.ui.graphics.Color

fun Color.adjust(factor: Float): Color {
    val a = alpha
    val r = red * factor
    val g = green * factor
    val b = blue * factor
    return Color(r.coerceIn(0f, 1f), g.coerceIn(0f, 1f), b.coerceIn(0f, 1f), a)
}