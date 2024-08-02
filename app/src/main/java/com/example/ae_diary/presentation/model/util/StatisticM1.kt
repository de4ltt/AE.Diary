package com.example.ae_diary.presentation.model.util

data class StatisticM1(
    val subjectId: Int,
    val studentId: Int,
    val absence: Int = 0,
    val resAbsence: Int = 0,
    val absencePercent: Int = 0
)
