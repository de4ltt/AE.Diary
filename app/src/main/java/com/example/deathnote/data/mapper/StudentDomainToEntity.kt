package com.example.deathnote.data.mapper

import com.example.deathnote.data.model.Students
import com.example.deathnote.domain.model.StudentDomain

fun StudentDomain.toEntity() = Students(
    id = id,
    name = name,
    surname = surname,
    patronymic = patronymic
)