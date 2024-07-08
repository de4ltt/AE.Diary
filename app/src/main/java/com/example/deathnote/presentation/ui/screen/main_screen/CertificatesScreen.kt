package com.example.deathnote.presentation.ui.screen.main_screen

import androidx.activity.compose.BackHandler
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Certificate
import com.example.deathnote.presentation.model.event.CertificateUIEvent
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarTextField
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarWithTextFields
import com.example.deathnote.presentation.ui.cross_screen_ui.DarkTopBar
import com.example.deathnote.presentation.ui.screen.main_screen.components.certificates_screen_ui.CertificatePane
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.viewmodel.CertificateViewModel
import com.example.deathnote.presentation.viewmodel.StudentViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
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

    val allCertificates =
        certificateViewModel.allCertificates.collectAsStateWithLifecycle().value.groupBy {
            LocalDate.parse(it.start, formatter).month + LocalDate.parse(
                it.start,
                formatter
            ).year.toLong()
        }

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
                    certificateViewModel.onEvent(CertificateUIEvent.ChangeDialogState(true))
                }
            )

            LazyColumn(
                contentPadding = paddingValues,
                verticalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                allCertificates.forEach { (_, items) ->
                    item {
                        Text(
                            text = LocalDate.parse(items[0].start, formatter).month.toString()
                                    + " " + LocalDate.parse(
                                items[0].start,
                                formatter
                            ).year.toString(),
                            style = DeathNoteTheme.typography.settingsScreenItemSubtitle,
                            color = DeathNoteTheme.colors.inverse
                        )
                    }

                    items(items) {
                        CertificatePane(
                            certificate = it,
                            student = studentViewModel.,
                            onEvent = certificateViewModel::onEvent
                        )
                    }

                    item {
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

            if (isBottomSheetShown) {
                BottomBarWithTextFields(
                    title = R.string.add_certificate,
                    onAcceptRequest = { },
                    onDismissRequest = { isBottomSheetShown = !isBottomSheetShown },
                    content = {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            BottomBarTextField(
                                title = R.string.student,
                                onValueChange = { studentName = it },
                                value = studentName,
                                isCentered = false,
                                innerTitle = R.string.enter_student
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
                                        onValueChange = { start = it },
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
                                        onValueChange = { end = it },
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
    }
}