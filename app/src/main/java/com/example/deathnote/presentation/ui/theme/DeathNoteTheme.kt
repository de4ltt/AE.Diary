package com.example.deathnote.presentation.ui.theme

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.deathnote.activity.MainActivity
import com.example.deathnote.presentation.model.ColorScheme
import com.example.deathnote.presentation.ui.theme.util.DeathNoteColors
import com.example.deathnote.presentation.ui.theme.util.ExtendedTheme
import com.example.deathnote.presentation.ui.theme.util.color_schemes.UserColorScheme

private var colorScheme by
    mutableStateOf(UserColorScheme.LightOddColorScheme)

@Composable
fun DeathNoteTheme(
    content: @Composable () -> Unit
) {
    ExtendedTheme(
        colors = colorScheme,
        content = content
    )
}

fun applyColorScheme(
    colors: DeathNoteColors,
    scheme: ColorScheme
) {
    when (scheme) {
        ColorScheme.EVEN_LIGHT -> UserColorScheme.LightEvenColorScheme = colors
        ColorScheme.ODD_LIGHT -> UserColorScheme.LightOddColorScheme = colors
        ColorScheme.EVEN_DARK -> TODO()
        ColorScheme.ODD_DARK -> TODO()
    }
}

fun getColorSchemeType(): ColorScheme = when (colorScheme) {
    UserColorScheme.LightOddColorScheme -> ColorScheme.ODD_LIGHT
    else -> ColorScheme.EVEN_LIGHT
}

fun setColorScheme(
    scheme: ColorScheme
) {
    colorScheme = when(scheme) {
        ColorScheme.EVEN_LIGHT -> UserColorScheme.LightEvenColorScheme
        ColorScheme.EVEN_DARK -> TODO()
        ColorScheme.ODD_LIGHT -> UserColorScheme.LightOddColorScheme
        ColorScheme.ODD_DARK -> TODO()
    }
}

fun switchDarkMode(
    context: Context
) {
    colorScheme = when (getColorSchemeType()) {
        ColorScheme.EVEN_LIGHT -> UserColorScheme.LightOddColorScheme
        ColorScheme.EVEN_DARK -> TODO()
        ColorScheme.ODD_LIGHT -> UserColorScheme.LightEvenColorScheme
        ColorScheme.ODD_DARK -> TODO()
    }
    (context as MainActivity).changeScheme(getColorSchemeType())
}

fun isDarkMode(): Boolean =
    getColorSchemeType() == ColorScheme.EVEN_LIGHT