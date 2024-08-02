package com.example.ae_diary.presentation.model

import com.example.ae_diary.presentation.model.interfaces.Nameable
import com.example.ae_diary.presentation.model.interfaces.PresentationModel

data class Subject(
    val id: Int = 0,
    val name: String = "",
    val type : String = "pr"
): PresentationModel, Nameable {

    override fun getShortName(): String {
        var res = ""

        val illicitLetters = "AEIOUYАЕЁИОУЭЮЯЇІЄaeiouyаееёиоуэюяіїє"

        if (this.name.split(" ").size == 1)
            res = this.name
        else
            for (part in this.name.split(" "))
                res +=
                    when {
                        part.length >= 3 -> {
                            if (!illicitLetters.contains(part[2])) part.slice(0..2) + ". "
                            else if (part.length >= 4) {
                                if (!illicitLetters.contains(part[3])) part.slice(0..3) + ". "
                                else part.slice(0..1) + ". "
                            } else part.slice(0..1) + ". "
                        }

                        else -> "$part "
                    }
        return res
    }

    override fun getFullName(): String = this.name

}