package com.example.deathnote.presentation.ui.screen.main_screen

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
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.DarkTopBar
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

    val allStudents by studentViewModel.allStudents.collectAsStateWithLifecycle()
    val diaryUIState by diaryViewModel.diaryUIState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .background(color = DeathNoteTheme.colors.baseBackground)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        DarkTopBar(
            destination = AppDestination.MainScreenMenusDestinations.DIARY,
            onIconClick = {

            }
        )

        diaryUIState.apply {
/*            ChangeSubject(
                paddingValues = paddingValues,
                state = diaryUIState,
                onEvent = diaryViewModel::onEvent
            )*/
            LazyColumn(
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(allStudents) {
                    StudentCard(
                        student = it,
                        state = diaryUIState,
                        titled = allStudents.indexOf(it) == 0,
                        isAbsent = diaryViewModel.isStudentAbsent(it),
                        isAbsRes = diaryViewModel.isStudentRespectfulAbs(it),
                        onEvent = diaryViewModel::onEvent
                    )
                }
            }
        }
    }
}