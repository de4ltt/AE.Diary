package com.example.deathnote.presentation.ui.theme.settings

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

data class DeathNoteShapes(
    val rounded12: RoundedCornerShape = RoundedCornerShape(12.dp),
    val rounded12_left: RoundedCornerShape = RoundedCornerShape(
        topStart = 12.dp,
        bottomStart = 12.dp
    )
)