package com.example.deathnote.presentation.model.event

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.example.deathnote.presentation.model.Student

sealed class StudentUIEvent {

    data class GetStudentById(val id: Int) : StudentUIEvent()
    data class DeleteStudent(val student: Student): StudentUIEvent()
    data class UpsertStudent(val student: Student): StudentUIEvent()

    data object IdleStudent: StudentUIEvent()
    data class SelectStudent(val student: Student): StudentUIEvent()
    data class ChangeStudentName(val name: String): StudentUIEvent()
    data class ChangeStudentSurname(val surname: String): StudentUIEvent()
    data class ChangeStudentPatronymic(val patronymic: String): StudentUIEvent()

    data class ChangeDialogState(val state: Boolean) : StudentUIEvent()
    data class ChangeDialogContent(
        val student: Student,
        @StringRes val title: Int,
        val onAcceptRequest: () -> Unit,
        val onDismissRequest: () -> Unit,
        val refuseButtonTitle: Int? = null,
        val acceptButtonTitle: Int? = null
    ) : StudentUIEvent()
}