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
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Certificate
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarTextField
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarWithTextFields
import com.example.deathnote.presentation.ui.cross_screen_ui.DarkTopBar
import com.example.deathnote.presentation.ui.screen.main_screen.certificates_screen_ui.CertificatePane
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Destination
@Composable
fun CertificatesScreen(
    navigator: DestinationsNavigator,
    paddingValues: PaddingValues = PaddingValues(
        start = 25.dp,
        end = 25.dp
    )
) {

    BackHandler {
        navigator.popBackStack()
    }

    var studentName by remember {
        mutableStateOf("")
    }

    var start by remember {
        mutableStateOf(
            LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        )
    }

    var end by remember {
        mutableStateOf(
            LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
        )
    }

    if (
        LocalDate.parse(start, DateTimeFormatter.ofPattern("dd.MM.yyyy")) >
            LocalDate.parse(end, DateTimeFormatter.ofPattern("dd.MM.yyyy"))
    ) {
        end = start
    }

    var isBottomSheetShown by rememberSaveable {
        mutableStateOf(false)
    }

    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

    val certificates = listOf(
        Certificate(1, "12.03.2024", "14.04.2024"),
        Certificate(2, "12.03.2024", "14.04.2024"),
        Certificate(3, "12.02.2024", "14.04.2024"),
        Certificate(4, "12.01.2024", "14.04.2024")
    ).groupBy {
        LocalDate.parse(it.start, formatter).month + LocalDate.parse(
            it.start,
            formatter
        ).year.toLong()
    }

    Column(
        modifier = Modifier
            .background(color = DeathNoteTheme.colors.baseBackground)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        DarkTopBar(
            destination = AppDestination.MainScreenMenusDestinations.CERTIFICATES,
            onIconClick = {
                isBottomSheetShown = true
            }
        )

        LazyColumn(
            contentPadding = paddingValues,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            certificates.forEach { (_, items) ->
                item {
                    Text(
                        text =  LocalDate.parse(items[0].start, formatter).month.toString()
                                + " " + LocalDate.parse(items[0].start, formatter).year.toString(),
                        style = DeathNoteTheme.typography.settingsScreenItemSubtitle,
                        color = DeathNoteTheme.colors.inverse
                    )
                }

                items(items) {
                    CertificatePane(
                        certificate = it,
                        onClick = { }
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
                            isCentered = false
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
                                    isStartDate = true
                                )
                            }

                            item {
                                BottomBarTextField(
                                    title = R.string.end_date,
                                    onValueChange = { end = it },
                                    previousDate = start,
                                    value = end,
                                    isDatePicker = true
                                )
                            }

                        }
                    }

                }
            )
        }
    }
}