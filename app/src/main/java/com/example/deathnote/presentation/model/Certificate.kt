package com.example.deathnote.presentation.model

data class Certificate(
    val id: Int? = null,
    val studentId: Int? = null,
    val start: String = "08:00",
    val end: String = "12:00",
): PresentationModel