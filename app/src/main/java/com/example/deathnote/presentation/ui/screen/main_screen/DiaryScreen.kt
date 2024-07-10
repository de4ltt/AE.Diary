package com.example.deathnote.presentation.ui.screen.main_screen

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Certificate
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.event.CertificateUIEvent
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarTextField
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarWithTextFields
import com.example.deathnote.presentation.ui.cross_screen_ui.DarkTopBar
import com.example.deathnote.presentation.ui.cross_screen_ui.NothingHere
import com.example.deathnote.presentation.ui.screen.main_screen.components.certificates_screen_ui.CertificatePane
import com.example.deathnote.presentation.ui.screen.main_screen.components.certificates_screen_ui.SelectStudentField
import com.example.deathnote.presentation.ui.screen.main_screen.components.certificates_screen_ui.StudentSelectMenu
import com.example.deathnote.presentation.ui.screen.main_screen.components.diary_screen_ui.ChangeSubject
import com.example.deathnote.presentation.ui.screen.main_screen.components.diary_screen_ui.StudentCard
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.util.toStringResMonth
import com.example.deathnote.presentation.viewmodel.CertificateViewModel
import com.example.deathnote.presentation.viewmodel.DiaryViewModel
import com.example.deathnote.presentation.viewmodel.StudentViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDate
import java.time.format.DateTimeFormatter

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
    val allDayAbsence by diaryViewModel.allDayAbsence.collectAsStateWithLifecycle()
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
            ChangeSubject(
                paddingValues = paddingValues,
                state = diaryUIState,
                onEvent = diaryViewModel::onEvent
            )
            LazyColumn(
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(allStudents) {
                    StudentCard(
                        student = it,
                        titled = allStudents.indexOf(it) == 0,
                        studentAbsence = allDayAbsence,
                        onEvent = diaryViewModel::onEvent
                    )
                }
            }
        }
    }
}