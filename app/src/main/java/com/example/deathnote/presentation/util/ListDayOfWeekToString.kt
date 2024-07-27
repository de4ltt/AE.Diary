package com.example.deathnote.presentation.util

import com.example.deathnote.presentation.model.util.DayOfWeek

fun List<DayOfWeek>.toDigitString(): String = buildString {
    this.forEach { dayOfWeek ->
        append(dayOfWeek.code)
    }
}