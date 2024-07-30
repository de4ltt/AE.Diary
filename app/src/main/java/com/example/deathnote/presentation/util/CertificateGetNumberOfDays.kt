package com.example.deathnote.presentation.util

import com.example.deathnote.presentation.model.Certificate
import java.time.LocalDate
import java.time.temporal.ChronoUnit

fun Certificate.getNumberOfDays(): Long = run {

    val startDate = LocalDate.parse(start, TimeFormatter.dateFormatter)
    val endDate = LocalDate.parse(end, TimeFormatter.dateFormatter)

    ChronoUnit.DAYS.between(startDate, endDate)
}