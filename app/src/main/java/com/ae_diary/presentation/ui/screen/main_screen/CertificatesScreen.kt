package com.ae_diary.presentation.ui.screen.main_screen

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
import com.ae_diary.presentation.model.Student
import com.ae_diary.presentation.model.event.CertificateUIEvent
import com.ae_diary.presentation.navigation.AppDestination
import com.ae_diary.presentation.navigation.transition.GeneralTransition
import com.ae_diary.presentation.ui.common.NothingHere
import com.ae_diary.presentation.ui.common.TopBar
import com.ae_diary.presentation.ui.screen.destinations.StudentsScreenDestination
import com.ae_diary.presentation.ui.screen.main_screen.components.certificates_screen_ui.AddCertificateBottomSheet
import com.ae_diary.presentation.ui.screen.main_screen.components.certificates_screen_ui.CertificatePane
import com.ae_diary.presentation.ui.screen.main_screen.components.certificates_screen_ui.CertificatesDatePickerWithDialog
import com.ae_diary.presentation.ui.screen.main_screen.components.certificates_screen_ui.Divider
import com.ae_diary.presentation.ui.screen.main_screen.components.certificates_screen_ui.MonthYearHeader
import com.ae_diary.presentation.ui.screen.main_screen.components.certificates_screen_ui.StudentSelectMenu
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.ae_diary.presentation.util.TimeFormatter.dateFormatter
import com.ae_diary.presentation.viewmodel.CertificateViewModel
import com.ae_diary.presentation.viewmodel.StudentViewModel
import com.ae_diary.presentation.viewmodel.TimetableViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDate

@Destination(style = GeneralTransition::class)
@Composable
fun CertificatesScreen(
    navigator: DestinationsNavigator,
    timetableViewModel: TimetableViewModel,
    certificateViewModel: CertificateViewModel,
    studentViewModel: StudentViewModel,
    paddingValues: PaddingValues = PaddingValues(
        start = 25.dp,
        end = 25.dp,
        bottom = 25.dp
    )
) {
    BackHandler {
        navigator.popBackStack()
    }

    val orderedCertificates by certificateViewModel.orderedCertificates.collectAsStateWithLifecycle()
    val allStudents by studentViewModel.allStudents.collectAsStateWithLifecycle()
    val certificatesUIState by certificateViewModel.certificateUIState.collectAsStateWithLifecycle()
    val semesterTime by timetableViewModel.semesterTime.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .background(color = DeathNoteTheme.colors.baseBackground)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {
        TopBar(
            destination = AppDestination.MainScreenDestinations.CERTIFICATES,
            onIconClick = {
                certificateViewModel.onEvent(CertificateUIEvent.ChangeStudent(Student()))
                certificateViewModel.onEvent(CertificateUIEvent.ChangeDialogState(true))
            }
        )

        NothingHere(
            modifier = Modifier.weight(1f),
            targetState = orderedCertificates.isEmpty()
        ) {

            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                orderedCertificates.forEach { (_, items) ->
                    item {
                        MonthYearHeader(items)
                    }

                    items(
                        items = items,
                        key = { it.id }
                    ) { certificate ->
                        CertificatePane(
                            certificate = certificate,
                            student = studentViewModel.getStudentById(certificate.studentId),
                            onEvent = certificateViewModel::onEvent
                        )
                    }

                    item {
                        Divider()
                    }
                }
            }
        }
    }

    AddCertificateBottomSheet(
        isStudentFieldActive = allStudents.isNotEmpty(),
        state = certificatesUIState,
        onEvent = certificateViewModel::onEvent,
        studentNavigate = {
            navigator.navigate(StudentsScreenDestination)
        }
    )

    StudentSelectMenu(
        onEvent = certificateViewModel::onEvent,
        state = certificatesUIState,
        allStudents = allStudents
    )

    CertificatesDatePickerWithDialog(
        semesterTime = Pair(
            LocalDate.parse(semesterTime.first, dateFormatter),
            LocalDate.parse(semesterTime.second, dateFormatter)
        ),
        onEvent = certificateViewModel::onEvent,
        state = certificatesUIState
    )
}
