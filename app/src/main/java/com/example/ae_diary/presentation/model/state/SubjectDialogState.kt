package com.example.ae_diary.presentation.model.state

import androidx.annotation.StringRes
import com.example.ae_diary.R
import com.example.ae_diary.presentation.model.Subject

data class SubjectDialogState(
    val subject: Subject = Subject(),
    val isShown: Boolean = false,
    @StringRes val title: Int = R.string.add_subject,
    val onAcceptRequest: () -> Unit = {},
    val onDismissRequest: () -> Unit = {},
    val refuseButtonTitle: Int? = null,
    val acceptButtonTitle: Int? = null
)