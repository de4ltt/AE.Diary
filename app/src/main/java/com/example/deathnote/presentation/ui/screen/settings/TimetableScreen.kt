package com.example.deathnote.presentation.ui.screen.settings

import androidx.activity.compose.BackHandler
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
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.ui.cross_screen_ui.SettingsTopBar
import com.example.deathnote.presentation.ui.screen.settings.components.timetable_screen_ui.TimetableCard
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.viewmodel.SubjectViewModel
import com.example.deathnote.presentation.viewmodel.TimetableViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlin.math.absoluteValue

@OptIn(ExperimentalFoundationApi::class)
@Destination
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

    val allTimetables by timetableViewModel.allTimetables.collectAsStateWithLifecycle()
    val timetableState by timetableViewModel.timetableState.collectAsStateWithLifecycle()

    val dayTimetables: Map<String, List<Timetable>> = allTimetables.groupBy {
        it.dayOfWeek
    }.filter { dayTimetable ->
        dayTimetable.key[0] == timetableState.weekType[0]
    }

    val pagerState = rememberPagerState(
        pageCount = { 6 },
    )

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
            state = pagerState,
            beyondBoundsPageCount = 1,
            verticalAlignment = Alignment.Top
        ) { page ->
            Card(
                Modifier
                    .padding(horizontal = 25.dp)
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
                    dayOfWeek = dayTimetables.keys.elementAt(page),
                    timetable = dayTimetables[dayTimetables.keys.elementAt(page)] ?: listOf(Timetable()),
                    getSubjectById = subjectViewModel::getSubjectById
                )
            }
        }
    }
}
