package com.example.deathnote.domain.model

data class TimetableDomain(
    val id: Int? = null,
    val dayOfWeek: String = "O_1",
    val subjectId: Int? = null,
    val startTime: String? = null,
    val endTime: String? = null
)
