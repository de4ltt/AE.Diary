package com.example.deathnote.presentation.model

data class StatisticM1(
    val subjectId: Int,
    val studentId: Int,
    val absence: Int,
    val resAbsence: Int,
    val absencePercent: Float
): PresentationModel
