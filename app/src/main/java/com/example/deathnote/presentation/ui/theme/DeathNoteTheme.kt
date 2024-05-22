package com.example.deathnote.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.example.deathnote.presentation.model.ColorScheme
import com.example.deathnote.presentation.ui.theme.util.DeathNoteColors
import com.example.deathnote.presentation.ui.theme.util.ExtendedTheme

var LightOddColorScheme = mutableStateOf(
    DeathNoteColors(
        primary = DarkYellow,
        primaryBackground = LightYellow,
        secondary = DarkRed,
        secondaryBackground = DarkRedBackground,
        inverse = Black,
        inverseBackground = SexyGray,
        lightInverse = SemiLightGray,
        regular = White,
        regularBackground = LightGray
    )
)

var LightEvenColorScheme = mutableStateOf(
    DeathNoteColors(
        primary = DarkRed,
        primaryBackground = LightRed,
        secondary = DarkRed,
        secondaryBackground = DarkRedBackground,
        inverse = Black,
        inverseBackground = SexyGray,
        lightInverse = SemiLightGray,
        regular = White,
        regularBackground = LightGray
    )
)

@Composable
fun DeathNoteTheme(
    isDarkMode: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {

    var colorScheme by remember {
        mutableStateOf(LightOddColorScheme)
    }

    colorScheme = when {
        isDarkMode -> LightEvenColorScheme
        else -> LightOddColorScheme
    }

    ExtendedTheme(
        colors = colorScheme.value,
        content = content
    )
}

fun applyColorScheme(
    colors: DeathNoteColors,
    scheme: ColorScheme
) {
    when (scheme) {
        ColorScheme.EVEN_LIGHT -> LightEvenColorScheme.value = colors
        ColorScheme.ODD_LIGHT -> LightOddColorScheme.value = colors
        ColorScheme.EVEN_DARK -> TODO()
        ColorScheme.ODD_DARK -> TODO()
    }
}