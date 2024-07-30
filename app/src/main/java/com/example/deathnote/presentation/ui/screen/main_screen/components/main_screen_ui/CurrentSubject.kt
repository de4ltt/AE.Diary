package com.example.deathnote.presentation.ui.screen.main_screen.components.main_screen_ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Timetable
import com.example.deathnote.presentation.model.state.MainScreenUIState
import com.example.deathnote.presentation.ui.theme.DarkRedBackground
import com.example.deathnote.presentation.ui.theme.DarkYellow
import com.example.deathnote.presentation.ui.theme.LightGray
import com.example.deathnote.presentation.ui.theme.SemiLightGray
import java.time.format.DateTimeFormatter

@Composable
fun CurrentSubject(
    state: MainScreenUIState
) {

    Column {
        state.apply {

            AnimatedVisibility(visible = isNextTimetableShown) {
                Row(
                    modifier = Modifier
                        .wrapContentHeight(),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.next),
                        style = TextStyle(
                            color = SemiLightGray,
                            fontSize = 30.sp,
                            lineHeight = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.navigate_arrow),
                        contentDescription = "arr_right",
                        modifier = Modifier
                            .size(20.dp)
                            .rotate(180f),
                        tint = SemiLightGray
                    )
                }
            }

            Crossfade(targetState = curSubject.name) {
                if (curTimetable == Timetable())
                    Text(
                        text = stringResource(id = R.string.no_subjects_appoined),
                        style = TextStyle(
                            color = LightGray,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                else
                    Text(
                        text = it.uppercase(),
                        style = TextStyle(
                            color = LightGray,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
            }

            if (curTimetable != Timetable())
                Spacer(modifier = Modifier.height(20.dp))

            Crossfade(targetState = isNextTimetableShown) {
                Text(
                    text = if (it && curTimetable != Timetable())
                        buildAnnotatedString {
                            withStyle(
                                style = SpanStyle(
                                    color = LightGray,
                                    fontSize = 19.sp,
                                    fontWeight = FontWeight.Bold
                                )
                            ) {
                                append(
                                    stringResource(
                                        R.string.break_until
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
                                append(
                                    if (curTimetable.date == curTime.format(
                                            DateTimeFormatter.ofPattern(
                                                "dd.MM.yyyy"
                                            )
                                        )
                                    )
                                        "\t\t${curTimetable.startTime}"
                                    else
                                        "\t\t${curTimetable.date} ${curTimetable.startTime}"
                                )
                            }
                        }
                    else if (curTimetable != Timetable())
                        buildAnnotatedString {
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
                    else buildAnnotatedString {}
                )
            }
        }
    }
}