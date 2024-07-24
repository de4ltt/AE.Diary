package com.example.deathnote.presentation.util

import com.example.deathnote.presentation.model.util.DayOfWeek

fun Int.toDayOfWeek() = when (this) {
    1 -> DayOfWeek.MONDAY
    2 -> DayOfWeek.TUESDAY
    3 -> DayOfWeek.WEDNESDAY
    4 -> DayOfWeek.THURSDAY
    5 -> DayOfWeek.FRIDAY
    else -> DayOfWeek.SATURDAY
}