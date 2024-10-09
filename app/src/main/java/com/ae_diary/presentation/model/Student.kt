package com.ae_diary.presentation.model

import com.ae_diary.presentation.model.interfaces.Nameable
import com.ae_diary.presentation.model.interfaces.PresentationModel

data class Student(
    val id: Int = 0,
    val name: String = "",
    val surname: String = "",
    val patronymic: String = ""
) : PresentationModel, Nameable {
    override fun getShortName(): String =
        if (this.name == "") "" else
            "${this.surname} ${this.name[0]}.${
                if (patronymic != "") "${patronymic[0]}." else ""
            }"

    override fun getFullName(): String =
        if (name == "") "" else
            "$surname $name $patronymic"

}

