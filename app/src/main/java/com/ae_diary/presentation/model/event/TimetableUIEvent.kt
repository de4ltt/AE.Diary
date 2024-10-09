package com.ae_diary.presentation.model.event

import com.ae_diary.presentation.model.Subject
import com.ae_diary.presentation.model.Timetable
import com.ae_diary.presentation.model.enums.SettingsDatePickerState
import com.ae_diary.presentation.model.enums.TimetableBottomSheetTimePickerState
import com.ae_diary.presentation.model.util.DayOfWeek

sealed class TimetableUIEvent {

    data class ChangeCurPage(val page: Int) : TimetableUIEvent()
    data object ChangeCurWeekType : TimetableUIEvent()
    data object UpsertTimetable : TimetableUIEvent()
    data class DeleteTimetable(val timetable: Timetable) : TimetableUIEvent()

    data object ChangeSemesterTime : TimetableUIEvent()
    data object IdleSemesterTime : TimetableUIEvent()

    data object ChangeBottomSheetState : TimetableUIEvent()
    data class ChangeBottomSheetSubject(val subject: Subject) : TimetableUIEvent()
    data class ChangeBottomSheetDayOfWeek(val dayOfWeek: DayOfWeek) : TimetableUIEvent()
    data object ChangeBottomSheetWeekType : TimetableUIEvent()
    data class ChangeBottomSheetStartTime(val startTime: String) : TimetableUIEvent()
    data class ChangeBottomSheetEndTime(val endTime: String) : TimetableUIEvent()

    data object ChangeBottomSheetSubjectPickerState : TimetableUIEvent()
    data class ChangeBottomSheetTimePickerState(val state: TimetableBottomSheetTimePickerState) : TimetableUIEvent()

    data class IdleBottomSheet(val dayOfWeek: DayOfWeek) : TimetableUIEvent()

    data class ChangeSettingsScreenBottomSheetDatePickerState(val state: SettingsDatePickerState) : TimetableUIEvent()

    data object ChangeSettingsScreenBottomSheetState : TimetableUIEvent()
    data class SettingsBottomSheetAddHoliday(val dayOfWeek: DayOfWeek) : TimetableUIEvent()
    data class SettingsBottomSheetDeleteHoliday(val dayOfWeek: DayOfWeek) : TimetableUIEvent()
    data class SettingsBottomSheetChangeSemesterStartTime(val time: String) : TimetableUIEvent()
    data class SettingsBottomSheetChangeSemesterEndTime(val time: String) : TimetableUIEvent()
    data object SettingsBottomSheetChangeFirstWeekType : TimetableUIEvent()
}
