package com.example.deathnote.presentation.model.state

import androidx.annotation.StringRes
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Student

data class StudentDialogState(
    var student: Student = Student(),
    val isShown: Boolean = false,
    @StringRes val title: Int = R.string.add_student,
    val onAcceptRequest: () -> Unit = {},
    val onDismissRequest: () -> Unit = {},
    val refuseButtonTitle: Int? = null,
    val acceptButtonTitle: Int? = null
)