package com.ae_diary.presentation.util

import com.ae_diary.R
import com.ae_diary.presentation.model.util.DayOfWeek

fun DayOfWeek.toShortDayOfWeek() = when (this) {
    DayOfWeek.MONDAY -> R.string.mon
    DayOfWeek.TUESDAY -> R.string.tue
    DayOfWeek.WEDNESDAY -> R.string.wed
    DayOfWeek.THURSDAY -> R.string.thu
    DayOfWeek.FRIDAY -> R.string.fri
    DayOfWeek.SATURDAY -> R.string.sat
    DayOfWeek.SUNDAY -> R.string.sun
}