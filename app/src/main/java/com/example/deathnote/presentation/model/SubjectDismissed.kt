package com.example.deathnote.presentation.model

import java.time.LocalDate

data class SubjectDismissed(
    val day: String = LocalDate.now().toString(),
    val subjectId: Int? = null
): PresentationModel