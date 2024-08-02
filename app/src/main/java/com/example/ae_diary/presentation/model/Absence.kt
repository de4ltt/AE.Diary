package com.example.ae_diary.presentation.model

import com.example.ae_diary.presentation.model.interfaces.PresentationModel

data class Absence(
    val studentId: Int,
    val respectful: Boolean,
    val timetableId: Int
): PresentationModel