package com.example.ae_diary.presentation.model.util

import androidx.annotation.StringRes
import com.example.ae_diary.R

enum class DayOfWeek(
    @StringRes val title: Int,
    val code: Int
) {

    MONDAY(R.string.monday, 1),
    TUESDAY(R.string.tuesday, 2),
    WEDNESDAY(R.string.wednesday, 3),
    THURSDAY(R.string.thursday, 4),
    FRIDAY(R.string.friday, 5),
    SATURDAY(R.string.saturday, 6),
    SUNDAY(R.string.sunday, 7)
}