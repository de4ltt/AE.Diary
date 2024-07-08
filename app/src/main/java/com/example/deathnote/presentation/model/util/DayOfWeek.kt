package com.example.deathnote.presentation.model.util

import androidx.annotation.StringRes
import com.example.deathnote.R

enum class DayOfWeek(
    @StringRes val title: Int,
    val code: String
) {

    MONDAY(R.string.monday, "1"),
    TUESDAY(R.string.tuesday, "2"),
    WEDNESDAY(R.string.wednesday, "3"),
    THURSDAY(R.string.thursday, "4"),
    FRIDAY(R.string.friday, "5"),
    SATURDAY(R.string.saturday, "6")
}