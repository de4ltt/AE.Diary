package com.example.deathnote.presentation.model

data class StatisticMM(
    val subjectId: Int,
    val presencePercent: Float,
    val resAbsencePercent: Float,
    val absencePercent: Float
): PresentationModel, StatisticModel
