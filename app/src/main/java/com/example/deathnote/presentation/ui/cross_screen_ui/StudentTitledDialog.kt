package com.example.deathnote.presentation.ui.cross_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.deathnote.R
import com.example.deathnote.presentation.model.event.StudentUIEvent
import com.example.deathnote.presentation.model.state.StudentDialogState
import com.example.deathnote.presentation.ui.theme.SexyGray
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.ui.theme.util.isDarkMode
import com.example.deathnote.presentation.ui.util.Validator

@Composable
fun StudentTitledDialog(
    state: StudentDialogState,
    onEvent: (StudentUIEvent) -> Unit
) {

    state.apply {

        if (isShown)
            BottomBarWithTextFields(
                title = title,
                onAcceptRequest = { onEvent(StudentUIEvent.UpsertStudent(student)) },
                onDismissRequest = { onEvent(StudentUIEvent.ChangeDialogState(false)) },
                isActive = Validator.run {
                    validateNameSurname(student.name) &&
                    validateNameSurname(student.surname) &&
                    validatePatronymic(student.patronymic)
                },
                content = {

                    BottomBarTextField(
                        title = R.string.name,
                        onValueChange = {
                            onEvent(StudentUIEvent.ChangeStudentName(it.capitalize()))
                        },
                        value = student.name,
                        isCentered = false
                    )

                    BottomBarTextField(
                        title = R.string.surname,
                        onValueChange = {
                            onEvent(StudentUIEvent.ChangeStudentSurname(it.capitalize()))
                        },
                        value = student.surname,
                        isCentered = false
                    )

                    BottomBarTextField(
                        title = R.string.patronymic,
                        onValueChange = {
                            onEvent(StudentUIEvent.ChangeStudentPatronymic(it.capitalize()))
                        },
                        value = student.patronymic,
                        isCentered = false
                    )

                }
            )

    }
}