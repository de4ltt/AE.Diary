package com.example.deathnote.presentation.model.util

import com.example.deathnote.R


fun String.toFormatDayOfWeek(): Int = when(this) {
    "O_1" -> R.string.monday
    "0_2" -> R.string.tuesday
    "0_3" -> R.string.wednesday
    "0_4" -> R.string.thursday
    "0_5" -> R.string.friday
    "0_6" -> R.string.saturday
    "E_1" -> R.string.monday
    "E_2" -> R.string.tuesday
    "E_3" -> R.string.wednesday
    "E_4" -> R.string.thursday
    "E_5" -> R.string.friday
    "E_6" -> R.string.saturday
    else -> R.string.sunday
}