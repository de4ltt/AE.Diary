package com.example.deathnote.presentation.model

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.security.cert.Certificate

data class Certificate(
    val studentId: Int,
    val start: String,
    val end: String
)