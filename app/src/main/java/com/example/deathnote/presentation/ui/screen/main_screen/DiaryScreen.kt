package com.example.deathnote.presentation.ui.screen.main_screen

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.viewmodel.DiaryViewModel
import com.example.deathnote.presentation.viewmodel.StudentViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination
@Composable
fun DiaryScreen(
    navigator: DestinationsNavigator,
    studentViewModel: StudentViewModel,
    diaryViewModel: DiaryViewModel,
    paddingValues: PaddingValues = PaddingValues(
        horizontal = 25.dp
    )
) {
/*
    BackHandler {
        navigator.popBackStack()
    }

    val diaryUIState = diaryViewModel.diaryUIState.collectAsStateWithLifecycle()
    val allStudents = studentViewModel.allStudents.collectAsStateWithLifecycle()

    diaryUIState.apply {

        Column(
            modifier = Modifier
                .background(color = DeathNoteTheme.colors.baseBackground)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {

            DarkTopBar(
                destination = AppDestination.MainScreenMenusDestinations.DIARY,
                onIconClick = {
                    diaryViewModel.onEvent(DiaryUIEvent.ChangeDatePickerState)
                }
            )
            ChangeSubject(
                isSubjectDismissed = isSubjectDismissed,
                paddingValues = paddingValues,
                state = diaryUIState,
                onEvent = diaryViewModel::onEvent
            )
            if (diaryUIState.curSubject.name.isNotEmpty())
                LazyColumn(
                    contentPadding = paddingValues,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(allStudents) { student ->
                        StudentCard(
                            student = student,
                            state = diaryUIState,
                            titled = allStudents.indexOf(student) == 0,
                            isAbsent = allAbsence.any { student.id == it.studentId && it.subjectId == curSubject.id && !it.respectful },
                            isAbsRes = allAbsence.any { student.id == it.studentId && it.subjectId == curSubject.id && it.respectful },
                            onEvent = diaryViewModel::onEvent
                        )
                    }
                }
            else
                NothingHere()
        }
    }

    DiaryDatePicker(
        isSelectableDate = diaryViewModel::isItWorkDay,
        state = diaryUIState,
        onEvent = diaryViewModel::onEvent
    )*/
}