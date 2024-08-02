package com.example.deathnote.presentation.util

import com.example.deathnote.R
import com.example.deathnote.presentation.model.util.DayOfWeek

fun DayOfWeek.toShortDayOfWeek() = when (this) {
    DayOfWeek.MONDAY -> R.string.mon
    DayOfWeek.TUESDAY -> R.string.tue
    DayOfWeek.WEDNESDAY -> R.string.wed
    DayOfWeek.THURSDAY -> R.string.thu
    DayOfWeek.FRIDAY -> R.string.fri
    DayOfWeek.SATURDAY -> R.string.sat
    DayOfWeek.SUNDAY -> R.string.sun
}