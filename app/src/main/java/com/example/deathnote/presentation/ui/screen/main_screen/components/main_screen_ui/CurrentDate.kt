package com.example.deathnote.presentation.ui.screen.main_screen.components.main_screen_ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.basicMarquee
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.deathnote.presentation.model.state.MainScreenUIState
import com.example.deathnote.presentation.ui.theme.DarkRedBackground
import com.example.deathnote.presentation.ui.theme.DarkYellow
import com.example.deathnote.presentation.ui.theme.LightGray
import com.example.deathnote.presentation.ui.theme.util.adjust
import com.example.deathnote.presentation.ui.theme.util.isEvenWeek
import com.example.deathnote.presentation.util.toDayOfWeek
import com.example.deathnote.presentation.util.toStringResMonth

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun CurrentDate(
    state: MainScreenUIState
) {

    state.apply {
        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = LightGray,
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(stringResource(id = curTime.dayOfWeek.value.toDayOfWeek().title).uppercase())
                }

                withStyle(
                    style = SpanStyle(
                        color = (if (isEvenWeek()) DarkRedBackground else DarkYellow).adjust(1.3f),
                        fontSize = 30.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(
                        "\t\t" + curTime.dayOfMonth.toString() + " " +
                                stringResource(id = curTime.month.toStringResMonth()).uppercase()
                    )
                }
            },
            modifier = Modifier.basicMarquee()
        )
    }
}