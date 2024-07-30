package com.example.deathnote.presentation.model.state

import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.util.TimeFormatter.nowDateFormatted

data class DiaryUIState(
    val curDate: String = nowDateFormatted,

    val curTimetable: Timetable = Timetable(),
    val curSubject: Subject = Subject(),
    val isSubjectDismissed: Boolean = false,

    val isDatePickerOpen: Boolean = false
)