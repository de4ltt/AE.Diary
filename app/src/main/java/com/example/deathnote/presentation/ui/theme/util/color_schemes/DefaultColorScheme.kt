package com.example.deathnote.presentation.ui.theme.util.color_schemes

import com.example.deathnote.presentation.ui.theme.Black
import com.example.deathnote.presentation.ui.theme.DarkRed
import com.example.deathnote.presentation.ui.theme.DarkRedBackground
import com.example.deathnote.presentation.ui.theme.DarkYellow
import com.example.deathnote.presentation.ui.theme.LightGray
import com.example.deathnote.presentation.ui.theme.LightRed
import com.example.deathnote.presentation.ui.theme.LightYellow
import com.example.deathnote.presentation.ui.theme.SemiLightGray
import com.example.deathnote.presentation.ui.theme.SexyGray
import com.example.deathnote.presentation.ui.theme.White
import com.example.deathnote.presentation.ui.theme.util.DeathNoteColors

object DefaultColorScheme {
    var LightOddColorScheme = DeathNoteColors(
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

    var LightEvenColorScheme = DeathNoteColors(
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
}