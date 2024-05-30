package com.example.deathnote.presentation.ui.theme.settings.color_schemes

import com.example.deathnote.presentation.ui.theme.Black
import com.example.deathnote.presentation.ui.theme.DarkRed
import com.example.deathnote.presentation.ui.theme.DarkRedBackground
import com.example.deathnote.presentation.ui.theme.DarkSoftGray
import com.example.deathnote.presentation.ui.theme.DarkYellow
import com.example.deathnote.presentation.ui.theme.DarkestSoftGray
import com.example.deathnote.presentation.ui.theme.LightGray
import com.example.deathnote.presentation.ui.theme.LightRed
import com.example.deathnote.presentation.ui.theme.LightYellow
import com.example.deathnote.presentation.ui.theme.LighterRed
import com.example.deathnote.presentation.ui.theme.LighterYellow
import com.example.deathnote.presentation.ui.theme.SemiLightGray
import com.example.deathnote.presentation.ui.theme.SexyGray
import com.example.deathnote.presentation.ui.theme.SoftGray
import com.example.deathnote.presentation.ui.theme.White
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteColors

object DefaultColorScheme {
    val LightOddColorScheme = DeathNoteColors(
        primary = DarkYellow,
        primaryDefault = DarkYellow,
        primaryBackground = LightYellow,
        secondary = DarkRed,
        secondaryBackground = DarkRedBackground,
        inverse = Black,
        inverseBackground = SexyGray,
        lightInverse = SemiLightGray,
        regular = White,
        regularBackground = LightGray,
        baseBackground = White,
        tertiary = LighterYellow
    )

    val LightEvenColorScheme = DeathNoteColors(
        primary = DarkRed,
        primaryDefault = DarkYellow,
        primaryBackground = LightRed,
        secondary = DarkRed,
        secondaryBackground = DarkRedBackground,
        inverse = Black,
        inverseBackground = SexyGray,
        lightInverse = SemiLightGray,
        regular = White,
        regularBackground = LightGray,
        baseBackground = White,
        tertiary = LighterRed
    )

    val DarkOddColorScheme = DeathNoteColors(
        primary = DarkYellow,
        primaryDefault = DarkYellow,
        primaryBackground = LightYellow,
        secondary = DarkRed,
        secondaryBackground = DarkRedBackground,
        inverse = SoftGray,
        inverseBackground = DarkSoftGray,
        lightInverse = SemiLightGray,
        regular = White,
        regularBackground = SexyGray,
        baseBackground = DarkestSoftGray,
        tertiary = LighterYellow
    )

    val DarkEvenColorScheme = DeathNoteColors(
        primary = DarkRed,
        primaryDefault = DarkYellow,
        primaryBackground = LightRed,
        secondary = DarkRed,
        secondaryBackground = DarkRedBackground,
        inverse = SoftGray,
        inverseBackground = DarkSoftGray,
        lightInverse = SemiLightGray,
        regular = White,
        regularBackground = SexyGray,
        baseBackground = DarkestSoftGray,
        tertiary = LighterRed
    )
}