package com.example.ae_diary.presentation.model.state

import androidx.annotation.StringRes
import com.example.ae_diary.R
import com.example.ae_diary.presentation.model.Student

data class StudentDialogState(
    var student: Student = Student(),
    val isShown: Boolean = false,
    @StringRes val title: Int = R.string.add_student,
    val onAcceptRequest: () -> Unit = {},
    val onDismissRequest: () -> Unit = {},
    val refuseButtonTitle: Int? = null,
    val acceptButtonTitle: Int? = null
)