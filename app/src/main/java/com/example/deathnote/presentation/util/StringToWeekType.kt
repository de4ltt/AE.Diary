package com.example.deathnote.presentation.util

import com.example.deathnote.presentation.model.util.WeekType

fun String.toWeekType() = when (this) {
    "odd" -> WeekType.ODD
    else -> WeekType.EVEN
}