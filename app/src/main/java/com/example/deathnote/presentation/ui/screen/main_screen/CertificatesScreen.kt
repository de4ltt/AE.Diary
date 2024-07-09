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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
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
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.util.toStringResMonth
import com.example.deathnote.presentation.viewmodel.CertificateViewModel
import com.example.deathnote.presentation.viewmodel.StudentViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.CoroutineScope
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Destination
@Composable
fun CertificatesScreen(
    navigator: DestinationsNavigator,
    certificateViewModel: CertificateViewModel,
    studentViewModel: StudentViewModel,
    paddingValues: PaddingValues = PaddingValues(
        start = 25.dp,
        end = 25.dp
    )
) {

    BackHandler {
        navigator.popBackStack()
    }

    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    val orderedCertificates by certificateViewModel.orderedCertificates.collectAsStateWithLifecycle()

    val allStudents by studentViewModel.allStudents.collectAsStateWithLifecycle()

    val certificatesUIState by certificateViewModel.certificateUIState.collectAsStateWithLifecycle()

    certificatesUIState.apply {
        Column(
            modifier = Modifier
                .background(color = DeathNoteTheme.colors.baseBackground)
                .fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(40.dp)
        ) {

            DarkTopBar(
                destination = AppDestination.MainScreenMenusDestinations.CERTIFICATES,
                onIconClick = {
                    certificateViewModel.onEvent(CertificateUIEvent.ChangeStudent(Student()))
                    certificateViewModel.onEvent(CertificateUIEvent.ChangeDialogState(true))
                }
            )

            if (orderedCertificates.isEmpty())
                NothingHere()
            else
                LazyColumn(
                    contentPadding = paddingValues,
                    verticalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    orderedCertificates.forEach { (_, items) ->
                        item {
                            Text(
                                text = stringResource(
                                    id = LocalDate.parse(
                                        items[0].start,
                                        formatter
                                    ).month.toStringResMonth()
                                )
                                        + " " + LocalDate.parse(
                                    items[0].start,
                                    formatter
                                ).year.toString(),
                                style = DeathNoteTheme.typography.settingsScreenItemSubtitle,
                                color = DeathNoteTheme.colors.inverse
                            )
                        }

                        items(
                            items = items,
                            key = { it.id!! }
                        ) {
                            CertificatePane(
                                certificate = it,
                                student = studentViewModel.getStudentById(it.studentId),
                                onEvent = certificateViewModel::onEvent
                            )
                        }

                        item {
                            AnimatedVisibility(
                                visible = items.isNotEmpty(),
                                exit = shrinkVertically(
                                    animationSpec = tween(durationMillis = 500),
                                    shrinkTowards = Alignment.Top
                                ) + fadeOut()
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(5.dp),
                                    contentAlignment = Alignment.Center,
                                    content = {
                                        Box(
                                            modifier = Modifier
                                                .height(3.dp)
                                                .fillMaxWidth(0.5f)
                                                .background(
                                                    color = DeathNoteTheme.colors.regularBackground
                                                )
                                        )
                                    }
                                )
                            }
                        }
                    }
                }

            if (isBottomSheetShown) {
                BottomBarWithTextFields(
                    title = R.string.add_certificate,
                    isActive = student.name != "",
                    onAcceptRequest = {
                        certificateViewModel.onEvent(
                            CertificateUIEvent.AddCertificate(
                                Certificate(
                                    studentId = student.id,
                                    start = start,
                                    end = end
                                )
                            )
                        )
                        certificateViewModel.onEvent(CertificateUIEvent.ChangeDialogState(false))
                    },
                    onDismissRequest = {
                        certificateViewModel.onEvent(CertificateUIEvent.ChangeDialogState(false))
                    },
                    content = {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            SelectStudentField(
                                student = student,
                                onEvent = certificateViewModel::onEvent
                            )

                            LazyVerticalGrid(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentHeight(),
                                columns = GridCells.Fixed(2),
                                horizontalArrangement = Arrangement.spacedBy(15.dp)
                            ) {
                                item {
                                    BottomBarTextField(
                                        title = R.string.start_date,
                                        onValueChange = {
                                            certificateViewModel.onEvent(
                                                CertificateUIEvent.ChangeStartDate(it)
                                            )
                                        },
                                        value = start,
                                        previousDate = start,
                                        isDatePicker = true,
                                        isStartDate = true,
                                        innerTitle = R.string.enter_start_date
                                    )
                                }

                                item {
                                    BottomBarTextField(
                                        title = R.string.end_date,
                                        onValueChange = {
                                            certificateViewModel.onEvent(
                                                CertificateUIEvent.ChangeEndDate(it)
                                            )
                                        },
                                        previousDate = start,
                                        value = end,
                                        isDatePicker = true,
                                        innerTitle = R.string.enter_end_date
                                    )
                                }

                            }
                        }

                    }
                )
            }
        }

        if (isSelectStudentSheetShown)
            StudentSelectMenu(
                onSelect = {
                    certificateViewModel.onEvent(CertificateUIEvent.ChangeStudent(it))
                    certificateViewModel.onEvent(CertificateUIEvent.ChangeStudentSheetState(false))
                },
                allStudents = allStudents,
                onDismissRequest = {
                    certificateViewModel.onEvent(CertificateUIEvent.ChangeStudentSheetState(false))
                }
            )
    }
}