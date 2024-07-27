package com.example.deathnote.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Alignment
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.deathnote.activity.util.hideSystemUi
import com.example.deathnote.activity.util.loadLanguagePreference
import com.example.deathnote.activity.util.loadSchemePreference
import com.example.deathnote.activity.util.saveSchemePreference
import com.example.deathnote.activity.util.setDisplayCutoutMode
import com.example.deathnote.activity.util.setLocale
import com.example.deathnote.presentation.model.util.ColorPresentation
import com.example.deathnote.presentation.navigation.NavigationUI
import com.example.deathnote.presentation.ui.theme.DeathNoteTheme
import com.example.deathnote.presentation.ui.theme.util.setColorScheme
import com.example.deathnote.presentation.viewmodel.CertificateViewModel
import com.example.deathnote.presentation.viewmodel.DiaryViewModel
import com.example.deathnote.presentation.viewmodel.MainScreenViewModel
import com.example.deathnote.presentation.viewmodel.StatisticsViewModel
import com.example.deathnote.presentation.viewmodel.StudentViewModel
import com.example.deathnote.presentation.viewmodel.SubjectViewModel
import com.example.deathnote.presentation.viewmodel.TimetableViewModel
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @OptIn(
        ExperimentalMaterialNavigationApi::class,
        ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val studentViewModel: StudentViewModel by viewModels()
        val subjectViewModel: SubjectViewModel by viewModels()
        val timetableViewModel: TimetableViewModel by viewModels()
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
            systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        })
        setDisplayCutoutMode()

        setContent {
            DeathNoteTheme {
                val navHostEngine =
                    rememberAnimatedNavHostEngine(navHostContentAlignment = Alignment.TopCenter)
                val navHostController = navHostEngine.rememberNavController()

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

    fun changeLanguage(language: String) =
        setLocale(this, language)

    fun changeScheme(colorMode: ColorPresentation.ColorMode) =
        saveSchemePreference(this, colorMode)
}



