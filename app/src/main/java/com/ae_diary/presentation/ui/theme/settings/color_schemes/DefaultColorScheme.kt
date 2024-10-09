package com.ae_diary.presentation.ui.theme.settings.color_schemes

import com.ae_diary.presentation.ui.theme.Black
import com.ae_diary.presentation.ui.theme.DarkRed
import com.ae_diary.presentation.ui.theme.DarkRedBackground
import com.ae_diary.presentation.ui.theme.DarkSoftGray
import com.ae_diary.presentation.ui.theme.DarkYellow
import com.ae_diary.presentation.ui.theme.DarkestSoftGray
import com.ae_diary.presentation.ui.theme.LightGray
import com.ae_diary.presentation.ui.theme.LightRed
import com.ae_diary.presentation.ui.theme.LightYellow
import com.ae_diary.presentation.ui.theme.LighterRed
import com.ae_diary.presentation.ui.theme.LighterYellow
import com.ae_diary.presentation.ui.theme.SemiLightGray
import com.ae_diary.presentation.ui.theme.SexyGray
import com.ae_diary.presentation.ui.theme.SoftGray
import com.ae_diary.presentation.ui.theme.White
import com.ae_diary.presentation.ui.theme.settings.DeathNoteColors

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