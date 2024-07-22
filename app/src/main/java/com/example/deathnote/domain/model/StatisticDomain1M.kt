package com.example.deathnote.domain.model

data class StatisticDomain1M(
    val subjectId: Int,
    val studentId: Int,
    val absence: Int,
    val resAbsence: Int,
    val absencePercent: Float
): DomainModel
