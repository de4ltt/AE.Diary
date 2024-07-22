package com.example.deathnote.domain.model

data class StatisticDomainM1(
    val subjectId: Int,
    val studentId: Int,
    val absence: Int,
    val resAbsence: Int,
    val absencePercent: Float
): DomainModel