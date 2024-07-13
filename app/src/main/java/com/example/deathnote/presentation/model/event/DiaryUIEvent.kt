package com.example.deathnote.presentation.model.event

import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.Subject

sealed class DiaryUIEvent {

    data object RefreshSubject: DiaryUIEvent()
    data object SetNextDaySubject: DiaryUIEvent()
    data object SetPreviousDaySubject: DiaryUIEvent()
    data object SetDaySubjectDismissed: DiaryUIEvent()
    data object UnsetDaySubjectDismissed: DiaryUIEvent()

    data object ChangeSettingsScreenBottomSheetState: DiaryUIEvent()
    data class ChangeStartOfSemester(val start: String): DiaryUIEvent()
    data class ChangeEndOfSemester(val end: String): DiaryUIEvent()
    data object ChangeFirstWeekType: DiaryUIEvent()
    data object ChangeSetSemesterTime: DiaryUIEvent()

    data object ChangeDatePickerState: DiaryUIEvent()
    data class ChangeDate(val date: String): DiaryUIEvent()

    data object AddSemesterTime: DiaryUIEvent()
    data class AddHoliday(val day: Int): DiaryUIEvent()
    data class DeleteHoliday(val day: Int): DiaryUIEvent()

    data object DeleteSemester: DiaryUIEvent()

    data class SetDaySubjectStudentAbsent(val student: Student, val daySubject: Subject, val date: String = ""): DiaryUIEvent()
    data class SetDaySubjectStudentPresent(val student: Student, val daySubject: Subject, val date: String = ""): DiaryUIEvent()

    data class SetDaySubjectStudentAbsentRespectful(val student: Student, val daySubject: Subject, val date: String): DiaryUIEvent()
    data class SetDaySubjectStudentPresentRespectful(val student: Student, val daySubject: Subject, val date: String): DiaryUIEvent()

    data class SetDay(val day: String): DiaryUIEvent()
}
