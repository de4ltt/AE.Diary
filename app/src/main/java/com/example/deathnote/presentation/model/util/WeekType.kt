package com.example.deathnote.presentation.model.util

import androidx.annotation.StringRes
import com.example.deathnote.R

enum class WeekType(
    val weekType: String,
    @StringRes val stringRes: Int
) {

    ODD("odd", R.string.odd),
    EVEN("even", R.string.even)

}