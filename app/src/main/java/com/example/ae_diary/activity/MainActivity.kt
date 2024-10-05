package com.example.ae_diary.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.toArgb
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import com.example.ae_diary.activity.util.curWeekType
import com.example.ae_diary.activity.util.hideSystemUi
import com.example.ae_diary.activity.util.loadLanguagePreference
import com.example.ae_diary.activity.util.loadSchemePreference
import com.example.ae_diary.activity.util.setDisplayCutoutMode
import com.example.ae_diary.activity.util.setLocale
import com.example.ae_diary.presentation.model.util.ColorPresentation
import com.example.ae_diary.presentation.model.util.WeekType
import com.example.ae_diary.presentation.navigation.NavigationUI
import com.example.ae_diary.presentation.ui.theme.DeathNoteTheme
import com.example.ae_diary.presentation.ui.theme.White
import com.example.ae_diary.presentation.ui.theme.util.isEvenWeek
import com.example.ae_diary.presentation.ui.theme.util.setColorScheme
import com.example.ae_diary.presentation.ui.theme.util.switchWeekTypeScheme
import com.example.ae_diary.presentation.util.TimeFormatter.curTimeFlow
import com.example.ae_diary.presentation.util.TimeFormatter.dateFormatter
import com.example.ae_diary.presentation.viewmodel.CertificateViewModel
import com.example.ae_diary.presentation.viewmodel.DiaryViewModel
import com.example.ae_diary.presentation.viewmodel.MainScreenViewModel
import com.example.ae_diary.presentation.viewmodel.StatisticsViewModel
import com.example.ae_diary.presentation.viewmodel.StudentViewModel
import com.example.ae_diary.presentation.viewmodel.SubjectViewModel
import com.example.ae_diary.presentation.viewmodel.TimetableViewModel
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val timetableViewModel: TimetableViewModel by viewModels()

        lifecycleScope.launch {
            withContext(Dispatchers.IO) {
                curTimeFlow.collectLatest {
                    if (isEvenWeek() && curWeekType(
                            it.format(dateFormatter),
                            semesterTime = timetableViewModel.semesterTime.value
                        ) == WeekType.ODD
                    ) {
                        switchWeekTypeScheme(this@MainActivity)
                    }

                    if (!isEvenWeek() && curWeekType(
                            it.format(dateFormatter),
                            semesterTime = timetableViewModel.semesterTime.value
                        ) == WeekType.EVEN
                    ) {
                        switchWeekTypeScheme(this@MainActivity)
                    }
                }
            }
        }

        val studentViewModel: StudentViewModel by viewModels()
        val subjectViewModel: SubjectViewModel by viewModels()
        val certificateViewModel: CertificateViewModel by viewModels()
        val diaryViewModel: DiaryViewModel by viewModels()
        val statisticsViewModel: StatisticsViewModel by viewModels()
        val mainScreenViewModel: MainScreenViewModel by viewModels()

        loadLanguagePreference(this)?.let {
            setLocale(this, it)
        }

        loadSchemePreference(this)?.let {
            setColorScheme(ColorPresentation.ColorMode.valueOf(it))
        }

        WindowCompat.setDecorFitsSystemWindows(window, false)
        window.hideSystemUi(extraAction = {
            systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        })
        setDisplayCutoutMode()

        setContent {
            DeathNoteTheme {
                val navHostEngine = rememberAnimatedNavHostEngine(navHostContentAlignment = Alignment.TopCenter)
                val navHostController = navHostEngine.rememberNavController()

                window.statusBarColor = White.toArgb()

                NavigationUI(
                    navHostEngine = navHostEngine,
                    navHostController = navHostController,
                    studentViewModel = studentViewModel,
                    subjectViewModel = subjectViewModel,
                    timetableViewModel = timetableViewModel,
                    certificateViewModel = certificateViewModel,
                    diaryViewModel = diaryViewModel,
                    statisticsViewModel = statisticsViewModel,
                    mainScreenViewModel = mainScreenViewModel
                )
            }
        }
    }
}



