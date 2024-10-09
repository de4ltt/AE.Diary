package com.ae_diary.presentation.util

import com.ae_diary.presentation.model.util.WeekType

fun String.toWeekType() = when (this) {
    "odd" -> WeekType.ODD
    else -> WeekType.EVEN
}