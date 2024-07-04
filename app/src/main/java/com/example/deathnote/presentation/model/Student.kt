package com.example.deathnote.presentation.model

import androidx.compose.ui.text.capitalize

data class Student(
    val studentId: Int?,
    val name: String,
    val surname: String,
    val patronymic: String?,
    val absence: Int
)

