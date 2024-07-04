package com.example.deathnote.presentation.model

import androidx.annotation.StringRes
import com.example.deathnote.R

enum class DayOfWeek(
    @StringRes val title: Int,
    val code: String
) {

    MONDAY(R.string.monday, "MON"),
    TUESDAY(R.string.tuesday, "TUE"),
    WEDNESDAY(R.string.wednesday, "WED"),
    THURSDAY(R.string.thursday, "THU"),
    FRIDAY(R.string.friday, "FRI"),
    SATURDAY(R.string.saturday, "SAT"),
    SUNDAY(R.string.sunday, "SUN")
}