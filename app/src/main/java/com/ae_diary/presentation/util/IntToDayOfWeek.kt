package com.ae_diary.presentation.util

import com.ae_diary.presentation.model.util.DayOfWeek

fun Int.toDayOfWeek() = when (this) {
    1 -> DayOfWeek.MONDAY
    2 -> DayOfWeek.TUESDAY
    3 -> DayOfWeek.WEDNESDAY
    4 -> DayOfWeek.THURSDAY
    5 -> DayOfWeek.FRIDAY
    6 -> DayOfWeek.SATURDAY
    else -> DayOfWeek.SUNDAY
}