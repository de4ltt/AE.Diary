package com.example.deathnote.presentation.util

import com.example.deathnote.R


fun String.toFormatDayOfWeek(): Int = when(this) {
    "O_1" -> R.string.monday
    "O_2" -> R.string.tuesday
    "O_3" -> R.string.wednesday
    "O_4" -> R.string.thursday
    "O_5" -> R.string.friday
    "O_6" -> R.string.saturday
    "E_1" -> R.string.monday
    "E_2" -> R.string.tuesday
    "E_3" -> R.string.wednesday
    "E_4" -> R.string.thursday
    "E_5" -> R.string.friday
    "E_6" -> R.string.saturday
    else -> R.string.sunday
}