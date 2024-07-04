package com.example.deathnote.presentation.model.event

import com.example.deathnote.presentation.model.Student

sealed class StudentUIEvent {
    data class GetStudentById(val id: Int) : StudentUIEvent()
    data class DeleteStudent(val student: Student): StudentUIEvent()
    data class UpsertStudent(val student: Student): StudentUIEvent()
    data class ChangeDeleteModeState(val state: Boolean) : StudentUIEvent()

}