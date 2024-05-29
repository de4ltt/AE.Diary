package com.example.deathnote.presentation.ui.theme.util

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.example.deathnote.presentation.ui.theme.util.font_family.InterFontFamily

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

    val timeTableCard: TextStyle = TextStyle(
        fontSize = 20.sp,
        lineHeight = 18.sp,
        fontFamily = InterFontFamily.Bold
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
    ),

    val subjectCardTimetableTitle: TextStyle = TextStyle(
        fontFamily = InterFontFamily.Semibold,
        fontSize = 20.sp,
        lineHeight = 23.sp
    )
)