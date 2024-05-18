package com.example.deathnote.presentation.model

data class Student(
    val studentId: Int,
    val name: String,
    val surname: String,
    val patronymic: String?,
    val absence: Int
)

fun Student.getShortName() =
    "${this.surname} ${this.name[0]}.${
        if (patronymic != null) "$patronymic." else ""
    }"