package com.example.ae_diary.presentation.model

import com.example.ae_diary.presentation.model.interfaces.PresentationModel

data class Certificate(
    val id: Int = 0,
    val studentId: Int,
    val start: String,
    val end: String
): PresentationModel