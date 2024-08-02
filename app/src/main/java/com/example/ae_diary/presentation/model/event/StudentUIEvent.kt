package com.example.ae_diary.presentation.model.event

import androidx.annotation.StringRes
import com.example.ae_diary.presentation.model.Student

sealed class StudentUIEvent {

    data class DeleteStudent(val student: Student): StudentUIEvent()
    data class UpsertStudent(val student: Student): StudentUIEvent()

    data object IdleStudent: StudentUIEvent()
    data class SelectStudent(val student: Student): StudentUIEvent()
    data class ChangeStudentName(val name: String): StudentUIEvent()
    data class ChangeStudentSurname(val surname: String): StudentUIEvent()
    data class ChangeStudentPatronymic(val patronymic: String): StudentUIEvent()

    data class ChangeDialogState(val state: Boolean): StudentUIEvent()
    data class ChangeDialogTitle(@StringRes val title: Int): StudentUIEvent()
    data class ChangeDialogContent(
        val student: Student,
        val onAcceptRequest: () -> Unit,
        @StringRes val title: Int,
        val onDismissRequest: () -> Unit,
        val refuseButtonTitle: Int? = null,
        val acceptButtonTitle: Int? = null
    ) : StudentUIEvent()
}