package com.example.deathnote.activity

import android.app.Activity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.ui.Alignment
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.deathnote.activity.util.loadLanguagePreference
import com.example.deathnote.activity.util.loadSchemePreference
import com.example.deathnote.activity.util.saveSchemePreference
import com.example.deathnote.activity.util.setLocale
import com.example.deathnote.presentation.model.ColorScheme
import com.example.deathnote.presentation.navigation.NavigationUI
import com.example.deathnote.presentation.ui.theme.DeathNoteTheme
import com.example.deathnote.presentation.ui.theme.setColorScheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

class MainActivity : ComponentActivity() {

    @OptIn(
        ExperimentalMaterialNavigationApi::class,
        ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loadLanguagePreference(this)?.let {
            setLocale(this, it)
        }

        loadSchemePreference(this)?.let {
            setColorScheme(ColorScheme.valueOf(it))
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
                    context = this
                )
            }
        }
    }

    fun changeLanguage(language: String) =
        setLocale(this, language)

    fun changeScheme(colorScheme: ColorScheme) =
        saveSchemePreference(this, colorScheme)
}

private fun Window.hideSystemUi(extraAction: (WindowInsetsControllerCompat.() -> Unit)? = null) {
    WindowInsetsControllerCompat(this, this.decorView).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        extraAction?.invoke(controller)
    }
}

internal fun Activity.setDisplayCutoutMode() {
    window.attributes.layoutInDisplayCutoutMode =
        WindowManager.LayoutParams.LAYOUT_IN_DISPLAY_CUTOUT_MODE_ALWAYS
}

