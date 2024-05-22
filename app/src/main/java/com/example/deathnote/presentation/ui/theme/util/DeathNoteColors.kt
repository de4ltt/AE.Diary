package com.example.deathnote.presentation.ui.theme.util

import androidx.compose.ui.graphics.Color
import com.example.deathnote.presentation.ui.theme.Black
import com.example.deathnote.presentation.ui.theme.DarkRed
import com.example.deathnote.presentation.ui.theme.DarkRedBackground
import com.example.deathnote.presentation.ui.theme.DarkYellow
import com.example.deathnote.presentation.ui.theme.LightGray
import com.example.deathnote.presentation.ui.theme.LightYellow
import com.example.deathnote.presentation.ui.theme.SemiLightGray
import com.example.deathnote.presentation.ui.theme.SexyGray
import com.example.deathnote.presentation.ui.theme.White

data class DeathNoteColors(
    val primary: Color = DarkYellow,
    val primaryBackground: Color = LightYellow,
    val secondary: Color = DarkRed,
    val secondaryBackground: Color = DarkRedBackground,
    val inverse: Color = Black,
    val inverseBackground: Color = SexyGray,
    val lightInverse: Color = SemiLightGray,
    val regular: Color = White,
    val regularBackground: Color = LightGray
)