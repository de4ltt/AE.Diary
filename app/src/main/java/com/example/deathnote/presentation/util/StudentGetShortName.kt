package com.example.deathnote.presentation.util

import com.example.deathnote.presentation.model.Student

fun Student.getShortName() =
    "${this.surname} ${this.name[0]}.${
        if (patronymic != "") "${patronymic[0]}." else ""
    }"