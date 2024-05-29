package com.example.deathnote.presentation.model.util

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.deathnote.R
import com.example.deathnote.presentation.model.ColorPresentation
import com.example.deathnote.presentation.ui.theme.util.DeathNoteTheme

@Composable
fun ColorPresentation.ColorType.get(): Pair<Color, Int> =
    when (this) {
        ColorPresentation.ColorType.PRIMARY -> Pair(
            DeathNoteTheme.colors.primary,
            R.string.primary
        )

        ColorPresentation.ColorType.PRIMARY_BACKGROUND -> Pair(
            DeathNoteTheme.colors.primaryBackground,
            R.string.primary_background
        )

        ColorPresentation.ColorType.SECONDARY -> Pair(
            DeathNoteTheme.colors.secondary,
            R.string.secondary
        )

        ColorPresentation.ColorType.SECONDARY_BACKGROUND -> Pair(
            DeathNoteTheme.colors.secondaryBackground,
            R.string.secondary_background
        )

        ColorPresentation.ColorType.REGULAR -> Pair(
            DeathNoteTheme.colors.regular,
            R.string.regular
        )

        ColorPresentation.ColorType.REGULAR_BACKGROUND -> Pair(
            DeathNoteTheme.colors.regularBackground,
            R.string.regular_background
        )

        ColorPresentation.ColorType.INVERSE -> Pair(
            DeathNoteTheme.colors.inverse,
            R.string.inverse
        )

        ColorPresentation.ColorType.INVERSE_BACKGROUND -> Pair(
            DeathNoteTheme.colors.inverseBackground,
            R.string.inverse_background
        )

        ColorPresentation.ColorType.LIGHT_INVERSE -> Pair(
            DeathNoteTheme.colors.lightInverse,
            R.string.light_inverse
        )
    }