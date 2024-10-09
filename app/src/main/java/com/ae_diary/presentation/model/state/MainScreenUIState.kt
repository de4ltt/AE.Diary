package com.ae_diary.presentation.model.state

import com.ae_diary.presentation.model.Subject
import com.ae_diary.presentation.model.Timetable
import com.ae_diary.presentation.util.TimeFormatter.nowDateTime
import java.time.LocalDateTime

data class MainScreenUIState(
    val curTime: LocalDateTime = nowDateTime,
    val curSubject: Subject = Subject(),
    val curTimetable: Timetable = Timetable(),
    val isNextTimetableShown: Boolean = false,

    val isSizeReducedPane: Boolean = false,

    val percentage: Float = 0f
)