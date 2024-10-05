package com.example.ae_diary.presentation.util

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

object TimeFormatter {

    val timeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm")

    val dateFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    val dateTimeFormatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy'T'HH:mm")

    val nowDateFormatted: String = LocalDate.now().format(dateFormatter)

    val nowDate: LocalDate = LocalDate.now()

    val nowDateTime: LocalDateTime = LocalDateTime.now()

    val curTimeFlow = flow {
        while (true) {
            emit(LocalDateTime.now())
            delay(100)
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