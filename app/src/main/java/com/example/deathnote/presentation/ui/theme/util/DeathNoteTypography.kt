package com.example.deathnote.presentation.ui.theme.util

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

data class DeathNoteTypography(

    val topBar: TextStyle = TextStyle(
        fontSize = 27.sp,
        fontFamily = InterFontFamily.Bold
    ),

    val itemCardIndex: TextStyle = TextStyle(
        fontFamily = InterFontFamily.Bold,
        fontSize = 18.sp
    ),

    val itemCardTitle: TextStyle = TextStyle(
        fontSize = 16.sp,
        lineHeight = 18.sp,
        fontFamily = InterFontFamily.Regular
    ),

    val settingsScreenItemTitle: TextStyle = TextStyle(
        fontFamily = InterFontFamily.Bold,
        fontSize = 16.sp,
        lineHeight = 16.sp
    ),

    val settingsScreenItemSubtitle: TextStyle = TextStyle(
        fontFamily = InterFontFamily.Semibold,
        fontSize = 13.sp,
        lineHeight = 13.sp
    )
)