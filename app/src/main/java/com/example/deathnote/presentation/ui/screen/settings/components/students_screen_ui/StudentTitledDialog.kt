package com.example.deathnote.presentation.ui.screen.settings.components.students_screen_ui

import androidx.compose.runtime.Composable
import com.example.deathnote.R
import com.example.deathnote.presentation.model.event.StudentUIEvent
import com.example.deathnote.presentation.model.state.StudentDialogState
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarTextField
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarWithTextFields
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
                        isCentered = false,
                        innerTitle = R.string.enter_name
                    )

                    BottomBarTextField(
                        title = R.string.surname,
                        onValueChange = {
                            onEvent(StudentUIEvent.ChangeStudentSurname(it.capitalize()))
                        },
                        value = student.surname,
                        isCentered = false,
                        innerTitle = R.string.enter_surname
                    )

                    BottomBarTextField(
                        title = R.string.patronymic,
                        onValueChange = {
                            onEvent(StudentUIEvent.ChangeStudentPatronymic(it.capitalize()))
                        },
                        value = student.patronymic,
                        isCentered = false,
                        innerTitle = R.string.enter_patronymic
                    )

                }
            )

    }
}