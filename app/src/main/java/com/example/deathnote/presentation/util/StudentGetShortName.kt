package com.example.deathnote.presentation.util

import com.example.deathnote.presentation.model.Student

fun Student.getShortName() =
    if (this.name == "") "" else
        "${this.surname} ${this.name[0]}.${
            if (patronymic != "") "${patronymic[0]}." else ""
        }"