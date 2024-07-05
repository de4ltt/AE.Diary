package com.example.deathnote.presentation.model.state

data class StudentUIState(
    val isStudentBarDeleteMode: Boolean = false,
    val isAddStudentDialogOpen: Boolean = false,
    val isEditStudentDialogOpen: Boolean = false,
    val isDeleteStudentDialogOpen: Boolean = false
)