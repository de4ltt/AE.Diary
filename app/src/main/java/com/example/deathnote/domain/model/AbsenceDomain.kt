package com.example.deathnote.domain.model

data class AbsenceDomain(
    val studentId: Int,
    val timetableId: Int,
    val respectful: Boolean = false
): DomainModel