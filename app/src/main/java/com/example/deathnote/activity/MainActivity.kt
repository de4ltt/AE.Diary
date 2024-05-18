package com.example.deathnote.activity

import android.app.Activity
import android.os.Build
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.deathnote.presentation.navigation.NavigationUI
import com.example.deathnote.presentation.ui.theme.DeathNoteTheme
import com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi
import com.ramcosta.composedestinations.animations.rememberAnimatedNavHostEngine

class MainActivity : ComponentActivity() {
    @OptIn(
        ExperimentalMaterialNavigationApi::class,
        ExperimentalMaterialNavigationApi::class, ExperimentalAnimationApi::class
    )
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

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
                    navHostController = navHostController
                )
            }
        }
    }
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
