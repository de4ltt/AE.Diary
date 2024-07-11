package com.example.deathnote.presentation.model

data class Student(
    val id: Int = 0,
    val name: String = "",
    val surname: String = "",
    val patronymic: String = ""
): PresentationModel

