@file:Suppress("WHEN_ENUM_CAN_BE_NULL_IN_JAVA")

package com.example.deathnote.presentation.util

import java.time.DayOfWeek
import java.time.LocalDate
import java.time.format.DateTimeFormatter

fun String.toTimetableDate(weekType: String) = weekType + when(LocalDate.parse(this, DateTimeFormatter.ofPattern("dd.MM.yyyy")).dayOfWeek) {
    DayOfWeek.MONDAY -> "_1"
    DayOfWeek.TUESDAY -> "_2"
    DayOfWeek.WEDNESDAY -> "_3"
    DayOfWeek.THURSDAY -> "_4"
    DayOfWeek.FRIDAY -> "_5"
    DayOfWeek.SATURDAY -> "_6"
    DayOfWeek.SUNDAY -> "_7"
}