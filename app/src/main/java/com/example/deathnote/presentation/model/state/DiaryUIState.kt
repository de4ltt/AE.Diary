package com.example.deathnote.presentation.model.state

import com.example.deathnote.presentation.model.Subject
import java.time.LocalDate

data class DiaryUIState(
    val curSubject: Subject = Subject(),
    val date: String = LocalDate.now().toString()
)