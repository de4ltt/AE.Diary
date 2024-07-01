package com.example.deathnote.presentation.ui.screen.settings.components.main_screen_ui

import androidx.compose.foundation.DefaultMarqueeIterations
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.deathnote.presentation.ui.theme.DarkRed
import com.example.deathnote.presentation.ui.theme.DarkRedBackground
import com.example.deathnote.presentation.ui.theme.DarkYellow
import com.example.deathnote.presentation.ui.theme.LightGray
import com.example.deathnote.presentation.ui.theme.LightYellow
import com.example.deathnote.presentation.ui.theme.util.adjust
import com.example.deathnote.presentation.ui.theme.util.isEvenWeek
import kotlinx.coroutines.delay
import java.time.LocalDateTime
import java.time.LocalTime

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrentDate() {
    var currentTime by remember {
        mutableStateOf(LocalDateTime.now())
    }

    LaunchedEffect(key1 = true) {
        while(true) {
            currentTime = LocalDateTime.now()
            delay(1000)
        }
    }

    Text(
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = LightGray,
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append(currentTime.dayOfWeek.toString())
            }

            withStyle(
                style = SpanStyle(
                    color = (if (isEvenWeek()) DarkRedBackground else DarkYellow).adjust(1.3f),
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("\t\t" + currentTime.dayOfMonth.toString() + " " + currentTime.month.toString())
            }
        },
        modifier = Modifier.basicMarquee()
    )
}