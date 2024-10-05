package com.example.ae_diary.presentation.ui.screen.settings.components.timetable_screen_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ae_diary.R
import com.example.ae_diary.presentation.model.enums.TimetableBottomSheetTimePickerState
import com.example.ae_diary.presentation.model.event.TimetableUIEvent
import com.example.ae_diary.presentation.model.state.TimetableUIState
import com.example.ae_diary.presentation.model.util.DayOfWeek
import com.example.ae_diary.presentation.model.util.WeekType
import com.example.ae_diary.presentation.ui.common.bottom_sheet.BottomBarTextField
import com.example.ae_diary.presentation.ui.common.bottom_sheet.BottomBarWithTextFields
import com.example.ae_diary.presentation.ui.theme.DarkRed
import com.example.ae_diary.presentation.ui.theme.DarkYellow
import com.example.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun TimetableBottomSheet(
    state: TimetableUIState,
    onEvent: (TimetableUIEvent) -> Unit
) {

    state.apply {
        if (bottomSheetState)
            BottomBarWithTextFields(
                title = R.string.add_subject,
                onAcceptRequest = {
                    onEvent(
                        TimetableUIEvent.UpsertTimetable
                    )
                    onEvent(TimetableUIEvent.ChangeBottomSheetState)
                },
                onDismissRequest = { onEvent(TimetableUIEvent.ChangeBottomSheetState) },
                isActive = bottomSheetSubject.name.isNotEmpty(),
                content = {

                    Column(
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        BottomBarTextField(
                            title = R.string.subject,
                            icon = {
                                Box(
                                    modifier = Modifier.width(40.dp),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = stringResource(
                                            id = if (bottomSheetSubject.type == "lk") R.string.lk
                                            else R.string.pr
                                        ).uppercase(),
                                        modifier = Modifier,
                                        color = if (bottomSheetSubject.type == "lk") DarkRed else DarkYellow,
                                        style = DeathNoteTheme.typography.textFieldTitle
                                    )
                                }
                            },
                            value = bottomSheetSubject.name,
                            isCentered = false,
                            innerTitle = R.string.choose_subject,
                            isActive = false,
                            onClick = {
                                onEvent(TimetableUIEvent.ChangeBottomSheetSubjectPickerState)
                            }
                        )

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(2),
                            modifier = Modifier.wrapContentHeight(),
                            horizontalArrangement = Arrangement.spacedBy(15.dp),
                            verticalArrangement = Arrangement.spacedBy(15.dp)
                        ) {
                            item {
                                FromListPicker(
                                    list = DayOfWeek.entries.map { it.code },
                                    title = R.string.day_of_week,
                                    value = bottomSheetDayOfWeek.title,
                                    onEvent = {
                                        onEvent(
                                            TimetableUIEvent.ChangeBottomSheetDayOfWeek(
                                                DayOfWeek.entries[(DayOfWeek.entries.indexOf(
                                                    bottomSheetDayOfWeek
                                                ) + 1) % 6]
                                            )
                                        )
                                    }
                                )
                            }
                            item {
                                FromListPicker(
                                    list = DayOfWeek.entries.map { it.code },
                                    title = R.string.week_type,
                                    value = if (bottomSheetWeekType == WeekType.ODD) R.string.odd_week else R.string.even_week,
                                    onEvent = {
                                        onEvent(TimetableUIEvent.ChangeBottomSheetWeekType)
                                    }
                                )
                            }

                            item {
                                TimeTextPicker(
                                    onClick = {
                                        onEvent(
                                            TimetableUIEvent.ChangeBottomSheetTimePickerState(
                                                TimetableBottomSheetTimePickerState.START
                                            )
                                        )
                                    },
                                    value = bottomSheetStartTime,
                                    title = R.string.start_time
                                )
                            }

                            item {
                                TimeTextPicker(
                                    onClick = {
                                        onEvent(
                                            TimetableUIEvent.ChangeBottomSheetTimePickerState(
                                                TimetableBottomSheetTimePickerState.END
                                            )
                                        )
                                    },
                                    value = bottomSheetEndTime,
                                    title = R.string.end_time
                                )
                            }
                        }
                    }
                }
            )
    }
}