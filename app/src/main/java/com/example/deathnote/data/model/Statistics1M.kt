package com.example.deathnote.data.model

data class Statistics1M(
    val subjectId: Int,
    val studentId: Int,
    val absence: Int,
    val resAbsence: Int,
    val absencePercent: Float
): DataEntity