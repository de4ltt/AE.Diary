package com.example.deathnote.presentation.model.util

import com.example.deathnote.presentation.model.Subject

fun Subject.getShortName() : String {
    var res = ""

    val illicitLetters = "AEIOUYАЕЁИОУЭЮЯЇІЄaeiouyаееёиоуэюяіїє"

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