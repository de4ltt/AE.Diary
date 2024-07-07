package com.example.deathnote.presentation.ui.theme.settings

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.ui.unit.dp

data class DeathNoteShapes(
    val rounded12: RoundedCornerShape = RoundedCornerShape(12.dp),

    val rounded12_left: RoundedCornerShape = RoundedCornerShape(
        topStart = 12.dp,
        bottomStart = 12.dp
    ),

    val rounded20_top: RoundedCornerShape = RoundedCornerShape(
        topStart = 20.dp,
        topEnd = 20.dp
    ),

    val rounded_bot_strt: RoundedCornerShape = RoundedCornerShape(
        bottomStart = 50.dp
    ),

    val rounded_bot_strt_mini: RoundedCornerShape = RoundedCornerShape(
        bottomStart = 35.dp
    ),

    val rounded_top_end: RoundedCornerShape = RoundedCornerShape(
        topEnd = 50.dp
    )
)