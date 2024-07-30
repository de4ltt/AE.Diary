package com.example.deathnote.presentation.model

import com.example.deathnote.presentation.model.interfaces.PresentationModel

data class Absence(
    val studentId: Int,
    val respectful: Boolean,
    val timetableId: Int
): PresentationModel