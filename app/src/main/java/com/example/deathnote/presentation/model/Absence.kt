package com.example.deathnote.presentation.model

data class Absence(
    val studentId: Int? = null,
    val subjectId: Int? = null,
    val respectful: Boolean? = false,
    val date: String? = null,
): PresentationModel