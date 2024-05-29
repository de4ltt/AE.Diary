package com.example.deathnote.presentation.ui.theme.util.color_schemes

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue

object UserColorScheme {
    var LightOddColorScheme by mutableStateOf(
        DefaultColorScheme.LightOddColorScheme
    )

    var LightEvenColorScheme by mutableStateOf(
        DefaultColorScheme.LightEvenColorScheme
    )

    var DarkOddColorScheme by mutableStateOf(
        DefaultColorScheme.DarkOddColorScheme
    )

    var DarkEvenColorScheme by mutableStateOf(
        DefaultColorScheme.DarkEvenColorScheme
    )
}