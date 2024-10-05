package com.example.ae_diary.presentation.ui.screen.settings

import androidx.activity.compose.BackHandler
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.graphics.drawscope.scale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.ae_diary.R
import com.example.ae_diary.presentation.model.event.TimetableUIEvent
import com.example.ae_diary.presentation.model.util.WeekType
import com.example.ae_diary.presentation.navigation.AppDestination
import com.example.ae_diary.presentation.navigation.transition.GeneralTransition
import com.example.ae_diary.presentation.ui.common.bottom_sheet.SettingsBottomButton
import com.example.ae_diary.presentation.ui.common.top_bar.SettingsTopBar
import com.example.ae_diary.presentation.ui.screen.settings.components.timetable_screen_ui.BottomSheetTimePicker
import com.example.ae_diary.presentation.ui.screen.settings.components.timetable_screen_ui.SubjectSelectMenu
import com.example.ae_diary.presentation.ui.screen.settings.components.timetable_screen_ui.TimetableBottomSheet
import com.example.ae_diary.presentation.ui.screen.settings.components.timetable_screen_ui.TimetableCard
import com.example.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.example.ae_diary.presentation.util.toDayOfWeek
import com.example.ae_diary.presentation.viewmodel.SubjectViewModel
import com.example.ae_diary.presentation.viewmodel.TimetableViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Destination(style = GeneralTransition::class)
@Composable
fun TimetableScreen(
    navigator: DestinationsNavigator,
    timetableViewModel: TimetableViewModel,
    subjectViewModel: SubjectViewModel,
    paddingValues: PaddingValues = PaddingValues(
        top = 50.dp,
        start = 25.dp,
        end = 25.dp
    )
) {

    BackHandler {
        navigator.popBackStack()
    }

    val timetableUIState by timetableViewModel.timetableUIState.collectAsStateWithLifecycle()

    val pagerState = rememberPagerState(
        pageCount = { 7 - timetableUIState.settingsBottomSheetHolidays.size },
    )

    val allSubjectsSize = subjectViewModel.allSubjects.collectAsStateWithLifecycle().value.size
    val daysOfWeek by timetableViewModel.daysOfWeek.collectAsStateWithLifecycle()
    val allTimetables by timetableViewModel.allTimetables.collectAsStateWithLifecycle()
    val availableSubjects by timetableViewModel.availableSubjects.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .background(color = DeathNoteTheme.colors.baseBackground)
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(40.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(paddingValues)
        ) {
            SettingsTopBar(
                destination = AppDestination.SettingsTopBarDestinations.TIMETABLE,
                onIconClick = {
                    navigator.popBackStack()
                }
            )
        }

        HorizontalPager(
            modifier = Modifier
                .weight(1f),
            state = pagerState,
            beyondBoundsPageCount = 1,
            verticalAlignment = Alignment.Top,
            contentPadding = PaddingValues(
                horizontal = 25.dp,
                vertical = 5.dp
            )
        ) { page ->
            timetableViewModel.onEvent(TimetableUIEvent.ChangeCurPage(pagerState.currentPage))
            Card(
                Modifier
                    .wrapContentSize()
                    .drawWithContent {
                        val pageOffset =
                            ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue

                        val scale = lerp(
                            start = 0.8f,
                            stop = 1f,
                            fraction = 1f - pageOffset.coerceIn(0f, 1f)
                        )

                        scale(scale, scale) {
                            this@drawWithContent.drawContent()
                        }
                    },
                colors = CardDefaults.cardColors(
                    containerColor = DeathNoteTheme.colors.baseBackground
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp,
                    draggedElevation = 4.dp,
                ),
                shape = DeathNoteTheme.shapes.rounded12
            ) {
                TimetableCard(
                    allSubjectsSize = allSubjectsSize,
                    dayOfWeek = daysOfWeek[page].toDayOfWeek(),
                    timetables = allTimetables[Pair(
                        daysOfWeek[page].toDayOfWeek(),
                        timetableUIState.curWeekType
                    )] ?: emptyList(),
                    getSubjectById = subjectViewModel::getSubjectById,
                    onEvent = timetableViewModel::onEvent
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 25.dp,
                    start = 25.dp,
                    end = 25.dp
                )
        ) {
            Crossfade(targetState = timetableUIState.curWeekType) {
                SettingsBottomButton(
                    title = if (it == WeekType.ODD) R.string.odd_week else R.string.even_week,
                    onClickAction = {
                        timetableViewModel.onEvent(TimetableUIEvent.ChangeCurWeekType)
                    }
                )
            }
        }
    }

    TimetableBottomSheet(
        state = timetableUIState,
        onEvent = timetableViewModel::onEvent
    )

    BottomSheetTimePicker(
        state = timetableUIState,
        onEvent = timetableViewModel::onEvent
    )

    SubjectSelectMenu(
        allSubjects = availableSubjects,
        onEvent = timetableViewModel::onEvent,
        state = timetableUIState
    )
}
