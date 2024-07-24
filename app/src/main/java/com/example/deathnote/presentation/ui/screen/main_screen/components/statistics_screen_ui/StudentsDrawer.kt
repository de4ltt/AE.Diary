package com.example.deathnote.presentation.ui.screen.main_screen.components.statistics_screen_ui

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.event.StatisticsUIEvent
import com.example.deathnote.presentation.model.state.StatisticsUIState
import com.example.deathnote.presentation.model.util.StatisticsMode
import com.example.deathnote.presentation.ui.screen.main_screen.components.certificates_screen_ui.StudentMenuBar
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.util.getShortName

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun StudentsDrawer(
    isActive: Boolean = true,
    studentsList: List<Student>,
    state: StatisticsUIState,
    onEvent: (StatisticsUIEvent) -> Unit,
    modifier: Modifier
) {

    state.apply {

        val rotationFloat by animateFloatAsState(
            targetValue = if (isStudentDrawerOpen) 180f else 0f
        )

        Row(
            modifier = modifier
                .clip(
                    DeathNoteTheme.shapes.rounded12
                )
                .background(
                    DeathNoteTheme.colors.regularBackground
                )
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        if (isActive)
                            onEvent(StatisticsUIEvent.ChangeStudentDrawerState)
                    }
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = if (mode != StatisticsMode.OneStudentManySubjects) stringResource(id = R.string.all_students)
                else curStudent.getShortName(),
                fontSize = 15.sp,
                color = DeathNoteTheme.colors.lightInverse,
                modifier = Modifier
                    .padding(start = 15.dp)
                    .weight(1f)
                    .basicMarquee()
            )

            Icon(
                painter = painterResource(R.drawable.navigate_arrow),
                contentDescription = "arrow_right",
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(15.dp)
                    .rotate(-90f + rotationFloat)
                    .animateContentSize(),
                tint = DeathNoteTheme.colors.lightInverse
            )
        }

        if (isStudentDrawerOpen) {

            ModalBottomSheet(
                onDismissRequest = {
                    onEvent(StatisticsUIEvent.ChangeStudentDrawerState)
                },
                dragHandle = {}
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .padding(
                            top = 25.dp,
                            end = 25.dp,
                            start = 25.dp
                        ),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    contentPadding = PaddingValues(bottom = 15.dp)
                ) {
                    if (studentsList.size > 1)
                        item {
                            StudentMenuBar(
                                student = Student(
                                    name = stringResource(id = R.string.all_students)
                                ),
                                onSelect = {
                                    onEvent(StatisticsUIEvent.ChangeMode(StatisticsMode.ManyStudentsOneSubject))
                                    onEvent(StatisticsUIEvent.ChangeStudentDrawerState)
                                }
                            )
                        }

                    itemsIndexed(studentsList) { index, student ->
                        StudentMenuBar(
                            student = student,
                            onSelect = {
                                onEvent(StatisticsUIEvent.ChangeStudent(student))
                                onEvent(StatisticsUIEvent.ChangeStudentDrawerState)
                            }
                        )
                    }
                }
            }
        }
    }
}