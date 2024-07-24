package com.example.deathnote.presentation.model

data class Statistic1M(
    val subjectId: Int,
    val studentId: Int,
    val absence: Int,
    val resAbsence: Int,
    val absencePercent: Float
): PresentationModel, StatisticModel