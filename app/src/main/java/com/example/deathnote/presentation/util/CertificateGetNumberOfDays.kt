package com.example.deathnote.presentation.util

import com.example.deathnote.presentation.model.Certificate
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

fun Certificate.getNumberOfDays(): Long = run {

    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    val startDate = LocalDate.parse(start, formatter)
    val endDate = LocalDate.parse(end, formatter)

    ChronoUnit.DAYS.between(startDate, endDate)
}