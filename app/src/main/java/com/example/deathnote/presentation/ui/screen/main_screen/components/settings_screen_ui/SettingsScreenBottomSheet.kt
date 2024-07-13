package com.example.deathnote.presentation.ui.screen.main_screen.components.settings_screen_ui

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SelectableDates
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.deathnote.R
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.model.state.DiaryUIState
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarTextField
import com.example.deathnote.presentation.ui.cross_screen_ui.BottomBarWithTextFields
import com.example.deathnote.presentation.ui.theme.SexyGray
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.ui.theme.util.isDarkMode
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreenBottomSheet(
    state: DiaryUIState,
    onEvent: (DiaryUIEvent) -> Unit
) {

    state.apply {
        if (isSettingsBottomSheetOpen) {
            if (!isTimeSet)
                BottomBarWithTextFields(
                    title = R.string.choose_semester_period,
                    isActive = true,
                    onAcceptRequest = {
                        onEvent(DiaryUIEvent.AddSemesterTime)
                        onEvent(DiaryUIEvent.ChangeSetSemesterTime)
                        onEvent(DiaryUIEvent.ChangeSettingsScreenBottomSheetState)
                    },
                    onDismissRequest = {
                        onEvent(DiaryUIEvent.ChangeSettingsScreenBottomSheetState)
                    },
                    content = {
                        Column(
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
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
                                            onEvent(DiaryUIEvent.ChangeStartOfSemester(it))
                                        },
                                        value = startOfSemester,
                                        previousDate = startOfSemester,
                                        isDatePicker = true,
                                        isStartDate = true,
                                        innerTitle = R.string.enter_start_date,
                                        previousDateSelector = object : SelectableDates {
                                            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                                                return utcTimeMillis >= LocalDate.now()
                                                    .toEpochDay() * 86400000
                                            }
                                        },
                                        futureDatesSelector = object : SelectableDates {
                                            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                                                return utcTimeMillis >= LocalDate.parse(
                                                    startOfSemester,
                                                    DateTimeFormatter.ofPattern("dd.MM.yyyy")
                                                ).toEpochDay() * 86400000
                                            }
                                        }
                                    )
                                }

                                item {
                                    BottomBarTextField(
                                        title = R.string.end_date,
                                        onValueChange = {
                                            onEvent(DiaryUIEvent.ChangeEndOfSemester(it))
                                        },
                                        previousDate = startOfSemester,
                                        value = endOfSemester,
                                        isDatePicker = true,
                                        innerTitle = R.string.enter_end_date,
                                        previousDateSelector = object : SelectableDates {
                                            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                                                return utcTimeMillis >= LocalDate.now()
                                                    .toEpochDay() * 86400000
                                            }
                                        },
                                        futureDatesSelector = object : SelectableDates {
                                            override fun isSelectableDate(utcTimeMillis: Long): Boolean {
                                                return utcTimeMillis >= LocalDate.parse(
                                                    startOfSemester,
                                                    DateTimeFormatter.ofPattern("dd.MM.yyyy")
                                                ).toEpochDay() * 86400000
                                            }
                                        }
                                    )
                                }
                            }

                            Column(
                                modifier = Modifier
                                    .wrapContentSize()
                            ) {

                                Text(
                                    text = stringResource(id = R.string.first_week),
                                    style = DeathNoteTheme.typography.textFieldTitle,
                                    color = DeathNoteTheme.colors.inverse
                                )


                                Box(
                                    modifier = Modifier
                                        .height(50.dp)
                                        .fillMaxWidth()
                                        .clip(
                                            shape = DeathNoteTheme.shapes.rounded12
                                        )
                                        .background(
                                            color = DeathNoteTheme.colors.baseBackground
                                        )
                                        .clickable(
                                            interactionSource = remember { MutableInteractionSource() },
                                            indication = null,
                                            onClick = {
                                                onEvent(DiaryUIEvent.ChangeFirstWeekType)
                                            }
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Crossfade(targetState = firstWeekType) {
                                        Text(
                                            text = stringResource(id = if (it == "O") R.string.odd else R.string.even).uppercase(),
                                            fontSize = 15.sp,
                                            color = DeathNoteTheme.colors.inverse,
                                            textAlign = TextAlign.Center,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(horizontal = 15.dp)
                                        )
                                    }
                                }

                            }
                            Column(
                                modifier = Modifier
                                    .wrapContentSize()
                            ) {
                                Text(
                                    text = stringResource(id = R.string.holidays),
                                    style = DeathNoteTheme.typography.textFieldTitle,
                                    color = DeathNoteTheme.colors.inverse
                                )

                                LazyRow(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .wrapContentHeight(),
                                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                                ) {
                                    for (i in 1..7)
                                        item {
                                            DayBox(
                                                day = i,
                                                state = state,
                                                onEvent = onEvent
                                            )
                                        }
                                }
                            }
                        }

                    }
                )
            else {
                Dialog(
                    onDismissRequest = {
                        onEvent(DiaryUIEvent.ChangeSettingsScreenBottomSheetState)
                    }
                ) {
                    Column(
                        modifier = Modifier
                            .padding(
                                vertical = 30.dp,
                                horizontal = 20.dp
                            )
                            .background(
                                DeathNoteTheme.colors.baseBackground
                            ),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(20.dp)
                    ) {
                        Text(
                            text = stringResource(id = R.string.delete_semester_period),
                            style = DeathNoteTheme.typography.settingsScreenItemTitle,
                            color = DeathNoteTheme.colors.inverse
                        )

                        Row(
                            modifier = Modifier.wrapContentSize(),
                            horizontalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .height(50.dp)
                                    .weight(1f)
                                    .clip(
                                        DeathNoteTheme.shapes.rounded12
                                    )
                                    .background(
                                        color = if (isDarkMode()) DeathNoteTheme.colors.baseBackground else SexyGray
                                    )
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = {
                                            onEvent(DiaryUIEvent.ChangeSettingsScreenBottomSheetState)
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(id = R.string.cancel),
                                    style = DeathNoteTheme.typography.settingsScreenItemTitle,
                                    color = Color.White
                                )
                            }

                            Box(
                                modifier = Modifier
                                    .height(50.dp)
                                    .weight(1f)
                                    .clip(
                                        DeathNoteTheme.shapes.rounded12
                                    )
                                    .background(
                                        DeathNoteTheme.colors.primary
                                    )
                                    .clickable(
                                        interactionSource = remember { MutableInteractionSource() },
                                        indication = null,
                                        onClick = {
                                            onEvent(DiaryUIEvent.DeleteSemester)
                                            onEvent(DiaryUIEvent.ChangeSetSemesterTime)
                                            onEvent(DiaryUIEvent.ChangeSettingsScreenBottomSheetState)
                                        }
                                    ),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = stringResource(id = R.string.ok),
                                    style = DeathNoteTheme.typography.settingsScreenItemTitle,
                                    color = Color.White
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}