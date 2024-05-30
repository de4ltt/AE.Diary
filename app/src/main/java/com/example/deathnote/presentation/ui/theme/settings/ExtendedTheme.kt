package com.example.deathnote.presentation.ui.theme.settings

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.staticCompositionLocalOf

val LocalDeathNoteColors = staticCompositionLocalOf { DeathNoteColors() }
val LocalDeathNoteTypography = staticCompositionLocalOf { DeathNoteTypography() }
val LocalDeathNoteShapes = staticCompositionLocalOf { DeathNoteShapes() }

@Composable
fun ExtendedTheme(
    colors: DeathNoteColors = DeathNoteColors(),
    typography: DeathNoteTypography = DeathNoteTypography(),
    shapes: DeathNoteShapes = DeathNoteShapes(),
    content: @Composable () -> Unit
) {

    CompositionLocalProvider(
        LocalDeathNoteColors provides colors,
        LocalDeathNoteTypography provides typography,
        LocalDeathNoteShapes provides shapes
    ) {
        content()
    }
}

object DeathNoteTheme {
    val colors: DeathNoteColors
        @Composable
        get() = LocalDeathNoteColors.current

    val typography: DeathNoteTypography
        @Composable
        get() = LocalDeathNoteTypography.current

    val shapes: DeathNoteShapes
        @Composable
        get() = LocalDeathNoteShapes.current
}