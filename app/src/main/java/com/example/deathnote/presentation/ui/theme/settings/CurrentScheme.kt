package com.example.deathnote.presentation.ui.theme.settings

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import com.example.deathnote.presentation.ui.theme.settings.color_schemes.DefaultColorScheme

var scheme: DeathNoteColors by mutableStateOf(
    DefaultColorScheme.LightOddColorScheme
)