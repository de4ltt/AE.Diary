package com.example.deathnote.presentation.ui.screen.main_screen

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.SnackbarDuration
import androidx.compose.material.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.deathnote.R
import com.example.deathnote.presentation.model.event.DiaryUIEvent
import com.example.deathnote.presentation.ui.screen.destinations.CertificatesScreenDestination
import com.example.deathnote.presentation.ui.screen.destinations.DiaryScreenDestination
import com.example.deathnote.presentation.ui.screen.destinations.SettingsScreenDestination
import com.example.deathnote.presentation.ui.screen.destinations.TimetableScreenDestination
import com.example.deathnote.presentation.ui.screen.main_screen.components.main_screen_ui.CurrentDate
import com.example.deathnote.presentation.ui.screen.main_screen.components.main_screen_ui.CurrentSubject
import com.example.deathnote.presentation.ui.screen.main_screen.components.main_screen_ui.MainScreenPane
import com.example.deathnote.presentation.ui.screen.main_screen.components.main_screen_ui.ProgressBar
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.viewmodel.DiaryViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import kotlinx.coroutines.launch


@Destination(start = true)
@Composable
fun MainScreen(
    diaryViewModel: DiaryViewModel,
    navigator: DestinationsNavigator
) {

    val context = LocalContext.current

    val snackbarHostState = remember { SnackbarHostState() }

    val scope = rememberCoroutineScope()

    val diaryUIState by diaryViewModel.diaryUIState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                color = DeathNoteTheme.colors.baseBackground
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .clip(
                    shape = DeathNoteTheme.shapes.rounded_bot_strt
                )
                .background(
                    color = DeathNoteTheme.colors.inverseBackground
                ),
            verticalArrangement = Arrangement.spacedBy(15.dp)
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 70.dp,
                    start = 40.dp,
                    end = 40.dp,
                    bottom = 40.dp
                ),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
                CurrentDate()
                CurrentSubject()
                ProgressBar()
            }
        }

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = DeathNoteTheme.colors.inverseBackground)
        ) {
            LazyVerticalGrid(
                modifier = Modifier
                    .fillMaxSize()
                    .clip(shape = DeathNoteTheme.shapes.rounded_top_end)
                    .background(color = DeathNoteTheme.colors.baseBackground),
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(40.dp),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    MainScreenPane(
                        topStartIcon = R.drawable.diary_tl,
                        middleEndIcon = R.drawable.diary_me,
                        title = R.string.diary_bar,
                        onClick = {
                            if (!diaryUIState.isTimeSet) {
                                navigator.navigate(SettingsScreenDestination)
                                diaryViewModel.onEvent(DiaryUIEvent.ChangeSettingsScreenBottomSheetState)

                                scope.launch {
                                    snackbarHostState.showSnackbar(
                                        context.getString(R.string.set_sem_time),
                                        duration = SnackbarDuration.Short
                                    )
                                }
                            }
                            else
                                navigator.navigate(DiaryScreenDestination)
                        }
                    )
                }

                item {
                    MainScreenPane(
                        topStartIcon = R.drawable.list_tl,
                        middleEndIcon = R.drawable.list_me,
                        title = R.string.list_bar,
                        onClick = {
                            navigator.navigate(CertificatesScreenDestination)
                        }
                    )
                }

                item {
                    MainScreenPane(
                        topStartIcon = R.drawable.stats_tl,
                        middleEndIcon = R.drawable.stats_me,
                        title = R.string.stats_bar
                    )
                }

                item {
                    MainScreenPane(
                        topStartIcon = R.drawable.settings_tl,
                        middleEndIcon = R.drawable.settings_me,
                        title = R.string.settings_bar,
                        onClick = {
                            navigator.navigate(SettingsScreenDestination)
                        }
                    )
                }
            }
        }
    }
}