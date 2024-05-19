package com.example.deathnote.presentation.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext

private val LightOddColorScheme = lightColorScheme(
    primary = DarkYellow,
    primaryContainer = LightYellow,
    secondary = DarkRed,
    secondaryContainer = LightRed,
    surfaceTint = LightGray,
    onPrimary = Black,
    onSecondary = SemiLightGray,
    inversePrimary = White
)

private val LightEvenColorScheme = lightColorScheme(
    primary = DarkRed,
    primaryContainer = LightRed,
    secondary = DarkRed,
    secondaryContainer = LightRed,
    surfaceTint = LightGray,
    onPrimary = Black,
    onSecondary = SemiLightGray,
    inversePrimary = White
)

@Composable
fun DeathNoteTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    var colorScheme by remember {
        mutableStateOf(LightOddColorScheme)
    }

    colorScheme = when {
        darkTheme -> LightEvenColorScheme
        else -> LightOddColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}