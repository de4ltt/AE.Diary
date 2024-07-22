package com.example.deathnote.presentation.model

data class Certificate(
    val id: Int = 0,
    val studentId: Int,
    val start: String,
    val end: String
): PresentationModel