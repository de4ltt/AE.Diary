package com.example.deathnote.presentation.util

import com.example.deathnote.presentation.model.Student

fun Student.getFullName() =
    if (name == "") "" else
        "$surname $name $patronymic"