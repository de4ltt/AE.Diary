package com.example.ae_diary.presentation.model.event

import com.example.ae_diary.presentation.model.Absence

sealed class DiaryUIEvent {

    data object SetNextSubject: DiaryUIEvent()
    data object SetPrevSubject: DiaryUIEvent()

    data class ChangeDate(val date: String): DiaryUIEvent()

    data class AddStudentAbsence(val absence: Absence): DiaryUIEvent()
    data class DeleteStudentAbsence(val absence: Absence): DiaryUIEvent()

    data object DismissSubject: DiaryUIEvent()
    data object UndismissSubject: DiaryUIEvent()

    data object ChangeDatePickerState: DiaryUIEvent()
}
