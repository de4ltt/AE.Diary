package com.ae_diary.presentation.model.state

import androidx.annotation.StringRes
import com.ae_diary.R
import com.ae_diary.presentation.model.Student

data class StudentDialogState(
    var student: Student = Student(),
    val isShown: Boolean = false,
    @StringRes val title: Int = R.string.add_student,
    val onAcceptRequest: () -> Unit = {},
    val onDismissRequest: () -> Unit = {},
    val refuseButtonTitle: Int? = null,
    val acceptButtonTitle: Int? = null
)