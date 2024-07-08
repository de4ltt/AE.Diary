package com.example.deathnote.presentation.util

import com.example.deathnote.R
import java.time.Month

fun Month.toStringResMonth(): Int = when (this.toString()) {
    "JANUARY" -> R.string.january
    "FEBRUARY" -> R.string.february
    "MARCH" -> R.string.march
    "APRIL" -> R.string.april
    "MAY" -> R.string.may
    "JUNE" -> R.string.june
    "JULY" -> R.string.july
    "AUGUST" -> R.string.august
    "SEPTEMBER" -> R.string.september
    "OCTOBER" -> R.string.october
    "NOVEMBER" -> R.string.november
    else -> R.string.december
}