package com.example.deathnote.presentation.ui.theme

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.deathnote.activity.MainActivity
import com.example.deathnote.presentation.model.ColorPresentation
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
    scheme: ColorPresentation.ColorMode
) {
    when (scheme) {
        ColorPresentation.ColorMode.EVEN_LIGHT -> UserColorScheme.LightEvenColorScheme = colors
        ColorPresentation.ColorMode.ODD_LIGHT -> UserColorScheme.LightOddColorScheme = colors
        ColorPresentation.ColorMode.EVEN_DARK -> UserColorScheme.DarkEvenColorScheme
        ColorPresentation.ColorMode.ODD_DARK -> UserColorScheme.DarkOddColorScheme
    }
}

fun getColorSchemeType(): ColorPresentation.ColorMode = when (colorScheme) {
    UserColorScheme.LightOddColorScheme -> ColorPresentation.ColorMode.ODD_LIGHT
    UserColorScheme.LightEvenColorScheme -> ColorPresentation.ColorMode.EVEN_LIGHT
    UserColorScheme.DarkEvenColorScheme -> ColorPresentation.ColorMode.EVEN_DARK
    else -> ColorPresentation.ColorMode.ODD_DARK
}

fun setColorScheme(
    scheme: ColorPresentation.ColorMode
) {
    colorScheme = when (scheme) {
        ColorPresentation.ColorMode.EVEN_LIGHT -> UserColorScheme.LightEvenColorScheme
        ColorPresentation.ColorMode.EVEN_DARK -> UserColorScheme.DarkEvenColorScheme
        ColorPresentation.ColorMode.ODD_LIGHT -> UserColorScheme.LightOddColorScheme
        ColorPresentation.ColorMode.ODD_DARK -> UserColorScheme.DarkOddColorScheme
    }
}

fun switchWeekTypeScheme(
    context: Context
) {
    colorScheme = when (getColorSchemeType()) {
        ColorPresentation.ColorMode.EVEN_LIGHT -> UserColorScheme.LightOddColorScheme
        ColorPresentation.ColorMode.EVEN_DARK -> UserColorScheme.DarkOddColorScheme
        ColorPresentation.ColorMode.ODD_LIGHT -> UserColorScheme.LightEvenColorScheme
        ColorPresentation.ColorMode.ODD_DARK -> UserColorScheme.DarkEvenColorScheme
    }
    (context as MainActivity).changeScheme(getColorSchemeType())
}
fun switchDarkMode(
    context: Context
) {
    colorScheme = when (getColorSchemeType()) {
        ColorPresentation.ColorMode.EVEN_LIGHT -> UserColorScheme.DarkEvenColorScheme
        ColorPresentation.ColorMode.EVEN_DARK -> UserColorScheme.LightEvenColorScheme
        ColorPresentation.ColorMode.ODD_LIGHT -> UserColorScheme.DarkOddColorScheme
        ColorPresentation.ColorMode.ODD_DARK -> UserColorScheme.LightOddColorScheme
    }
    (context as MainActivity).changeScheme(getColorSchemeType())
}

fun isDarkMode(): Boolean =
    getColorSchemeType() == ColorPresentation.ColorMode.EVEN_DARK || getColorSchemeType() == ColorPresentation.ColorMode.ODD_DARK