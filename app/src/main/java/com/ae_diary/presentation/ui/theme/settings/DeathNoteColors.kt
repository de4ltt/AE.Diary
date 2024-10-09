package com.ae_diary.presentation.ui.theme.settings

import androidx.compose.ui.graphics.Color
import com.ae_diary.presentation.ui.theme.Black
import com.ae_diary.presentation.ui.theme.DarkRed
import com.ae_diary.presentation.ui.theme.DarkRedBackground
import com.ae_diary.presentation.ui.theme.DarkYellow
import com.ae_diary.presentation.ui.theme.LightGray
import com.ae_diary.presentation.ui.theme.LightYellow
import com.ae_diary.presentation.ui.theme.LighterYellow
import com.ae_diary.presentation.ui.theme.SemiLightGray
import com.ae_diary.presentation.ui.theme.SexyGray
import com.ae_diary.presentation.ui.theme.White

data class DeathNoteColors(
    val primary: Color = DarkYellow,
    val primaryDefault: Color = DarkYellow,
    val primaryBackground: Color = LightYellow,
    val secondary: Color = DarkRed,
    val secondaryBackground: Color = DarkRedBackground,
    val inverse: Color = Black,
    val inverseBackground: Color = SexyGray,
    val lightInverse: Color = SemiLightGray,
    val regular: Color = White,
    val regularBackground: Color = LightGray,
    val baseBackground: Color = White,
    val tertiary: Color = LighterYellow
)