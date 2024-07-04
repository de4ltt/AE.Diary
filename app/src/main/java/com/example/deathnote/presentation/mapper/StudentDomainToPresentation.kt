package com.example.deathnote.presentation.mapper

import com.example.deathnote.domain.model.StudentDomain
import com.example.deathnote.presentation.model.Student

fun StudentDomain.toPresentation() = Student(
    id = id,
    name = name,
    surname = surname,
    patronymic = patronymic
)