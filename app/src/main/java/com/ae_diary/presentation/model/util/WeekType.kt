package com.ae_diary.presentation.model.util

import androidx.annotation.StringRes
import com.ae_diary.R

enum class WeekType(
    val weekType: String,
    @StringRes val stringRes: Int
) {

    ODD("odd", R.string.odd),
    EVEN("even", R.string.even)

}