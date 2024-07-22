package com.example.deathnote.presentation.ui.screen.main_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.DarkTopBar
import com.example.deathnote.presentation.ui.cross_screen_ui.NothingHere
import com.example.deathnote.presentation.ui.screen.main_screen.components.diary_screen_ui.ChangeSubject
import com.example.deathnote.presentation.ui.screen.main_screen.components.diary_screen_ui.DiaryDatePicker
import com.example.deathnote.presentation.ui.screen.main_screen.components.diary_screen_ui.StudentCard
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
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

    BackHandler {
        navigator.popBackStack()
    }

    diaryUIState.apply {

        val isSubjectDismissed = allDismissedSubjects.any {
            it.subjectId == curSubject.id && it.day == date
        }

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
    )
}