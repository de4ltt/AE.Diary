package com.example.deathnote.presentation.model

data class Absence(
    val studentId: Int,
    val subjectId: Int,
    val respectful: Boolean,
    val date: String,
): PresentationModel