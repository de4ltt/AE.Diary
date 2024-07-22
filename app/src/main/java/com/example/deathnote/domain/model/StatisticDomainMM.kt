package com.example.deathnote.domain.model

data class StatisticDomainMM(
    val subjectId: Int,
    val presencePercent: Float,
    val resAbsencePercent: Float,
    val absencePercent: Float
): DomainModel