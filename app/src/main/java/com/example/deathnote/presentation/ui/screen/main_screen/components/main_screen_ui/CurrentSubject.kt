package com.example.deathnote.presentation.ui.screen.main_screen.components.main_screen_ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.state.MainScreenUIState
import com.example.deathnote.presentation.ui.theme.DarkRedBackground
import com.example.deathnote.presentation.ui.theme.DarkYellow
import com.example.deathnote.presentation.ui.theme.LightGray
import com.example.deathnote.presentation.ui.theme.SemiLightGray

@Composable
fun CurrentSubject(
    state: MainScreenUIState
) {

    state.apply {

        Text(
            text = curSubject.name.uppercase(),
            style = TextStyle(
                color = LightGray,
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold
            )
        )

        Text(
            text = buildAnnotatedString {
                withStyle(
                    style = SpanStyle(
                        color = if (curSubject.type == "lk") DarkRedBackground
                        else DarkYellow,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append(
                        stringResource(
                            id = if (curSubject.type == "lk") R.string.lecture
                            else R.string.practice
                        )
                    )
                }

                withStyle(
                    style = SpanStyle(
                        color = SemiLightGray,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )
                ) {
                    append("\t\t${curTimetable.startTime}-${curTimetable.endTime}")
                }
            }
        )
    }
}