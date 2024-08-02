package com.example.ae_diary.presentation.ui.screen.main_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ae_diary.presentation.model.event.DiaryUIEvent
import com.example.ae_diary.presentation.navigation.AppDestination
import com.example.ae_diary.presentation.navigation.transition.GeneralTransition
import com.example.ae_diary.presentation.ui.cross_screen_ui.NothingHere
import com.example.ae_diary.presentation.ui.cross_screen_ui.top_bar.DarkTopBar
import com.example.ae_diary.presentation.ui.screen.main_screen.components.diary_screen_ui.ChangeSubject
import com.example.ae_diary.presentation.ui.screen.main_screen.components.diary_screen_ui.DiaryDatePicker
import com.example.ae_diary.presentation.ui.screen.main_screen.components.diary_screen_ui.StudentCard
import com.example.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.example.ae_diary.presentation.viewmodel.DiaryViewModel
import com.example.ae_diary.presentation.viewmodel.StudentViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator

@Destination(style = GeneralTransition::class)
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

    val allStudents by studentViewModel.allStudents.collectAsStateWithLifecycle()
    val allAbsence by diaryViewModel.allAbsence.collectAsStateWithLifecycle()
    val diaryUIState by diaryViewModel.diaryUIState.collectAsStateWithLifecycle()
    val allDayTimetables by diaryViewModel.allDayTimetables.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .background(color = DeathNoteTheme.colors.baseBackground)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        diaryUIState.apply {
            DarkTopBar(
                destination = AppDestination.MainScreenMenusDestinations.DIARY,
                onIconClick = {
                    diaryViewModel.onEvent(DiaryUIEvent.ChangeDatePickerState)
                }
            )
            ChangeSubject(
                isSubjectDismissed = allDayTimetables.any { it == curTimetable && it.isDismissed },
                paddingValues = paddingValues,
                state = diaryUIState,
                onEvent = diaryViewModel::onEvent
            )
            if (curSubject.name.isNotEmpty())
                LazyColumn(
                    contentPadding = paddingValues,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(allStudents) { student ->
                        StudentCard(
                            student = student,
                            state = diaryUIState,
                            titled = allStudents.indexOf(student) == 0,
                            isAbsent = allAbsence.any { student.id == it.studentId && it.timetableId == curTimetable.id && !it.respectful },
                            isAbsRes = allAbsence.any { student.id == it.studentId && it.timetableId == curTimetable.id && it.respectful },
                            onEvent = diaryViewModel::onEvent
                        )
                    }
                }
            else
                NothingHere()
        }
    }

    DiaryDatePicker(
        isSelectableDate = diaryViewModel::isItHoliday,
        state = diaryUIState,
        onEvent = diaryViewModel::onEvent
    )
}