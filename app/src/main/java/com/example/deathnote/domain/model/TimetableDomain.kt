package com.example.deathnote.domain.model

data class TimetableDomain(
    val id: Int,
    val date: String,
    val subjectId: Int,
    val startTime: String,
    val endTime: String,
    val weekType: String,
    val isDismissed: Boolean
): DomainModel
