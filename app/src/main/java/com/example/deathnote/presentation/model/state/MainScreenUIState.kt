package com.example.deathnote.presentation.model.state

import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.util.TimeFormatter.nowDateTime
import java.time.LocalDateTime

data class MainScreenUIState(
    val curTime: LocalDateTime = nowDateTime,
    val curSubject: Subject = Subject(),
    val curTimetable: Timetable = Timetable(),
    val isNextTimetableShown: Boolean = false,

    val percentage: Float = 0f
)