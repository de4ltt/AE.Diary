package com.example.deathnote.presentation.ui.screen.settings.components.main_screen_ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.ui.theme.LightGray
import com.example.deathnote.presentation.ui.theme.SemiLightGray
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatter.ofPattern

@Composable
fun ProgressBar(
    startTime: String = "16:00",
    endTime: String = "18:00"
) {
    val timeFormatter = ofPattern("HH:mm")
    val startTimeParsed = LocalTime.parse(startTime, timeFormatter).toSecondOfDay()
    val endTimeParsed = LocalTime.parse(endTime, timeFormatter).toSecondOfDay()
    var percentage by remember {
        mutableFloatStateOf(0f)
    }

    LaunchedEffect(key1 = true) {
        while(true) {
            percentage = (LocalTime.now()
                .toSecondOfDay() - startTimeParsed).toFloat() / (endTimeParsed - startTimeParsed)
            delay(1000)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(7.dp)
            .clip(DeathNoteTheme.shapes.rounded12)
            .background(SemiLightGray)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(fraction = percentage)
                .background(LightGray)
        )
    }
}