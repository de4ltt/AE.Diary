package com.ae_diary.presentation.model.state

import com.ae_diary.presentation.model.Subject
import com.ae_diary.presentation.model.Timetable
import com.ae_diary.presentation.util.TimeFormatter.nowDateFormatted

data class DiaryUIState(
    val curDate: String = nowDateFormatted,

    val curTimetable: Timetable = Timetable(),
    val curSubject: Subject = Subject(),
    val isSubjectDismissed: Boolean = false,

    val isDatePickerOpen: Boolean = false
)