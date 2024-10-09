package com.ae_diary.presentation.model

import com.ae_diary.presentation.model.interfaces.PresentationModel

data class Certificate(
    val id: Int = 0,
    val studentId: Int,
    val start: String,
    val end: String
): PresentationModel