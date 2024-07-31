package com.example.deathnote.presentation.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TimeFormatter {

    val timeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    val dateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy'T'HH:mm")

    val nowDateFormatted = LocalDate.now().format(dateFormatter)

    val nowDate = LocalDate.now()

    val nowDateTime = LocalDateTime.now()

    val curTimeFlow = flow {
        while (true) {
            emit(LocalDateTime.now())
            delay(1000)
        }
    }

    fun formatSelectedDate(dateMillis: Long?, curDate: String): String {
        return if (dateMillis != null) {
            LocalDate.ofEpochDay(dateMillis / 86400000).format(dateFormatter)
        } else {
            curDate
        }
    }
}