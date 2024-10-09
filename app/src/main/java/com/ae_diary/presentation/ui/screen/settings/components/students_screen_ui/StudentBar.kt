package com.ae_diary.presentation.ui.screen.settings.components.students_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.ae_diary.R
import com.ae_diary.presentation.model.Student
import com.ae_diary.presentation.model.event.StudentUIEvent
import com.ae_diary.presentation.ui.common.delete_container.SwipeToDeleteContainer
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun StudentBar(
    index: Int,
    student: Student,
    onEvent: (StudentUIEvent) -> Unit
) {

    val currentDensity = LocalDensity.current

    var containerHeight by remember {
        mutableStateOf(80.dp)
    }

    Row(
        modifier = Modifier
            .clip(DeathNoteTheme.shapes.rounded12)
    ) {
        SwipeToDeleteContainer(
            item = student,
            onDelete = {
                onEvent(StudentUIEvent.DeleteStudent(student))
            },
            icon = R.drawable.church
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .heightIn(min = 80.dp)
            ) {
                Box(
                    modifier = Modifier
                        .width(28.dp)
                        .heightIn(min = 80.dp)
                        .height(containerHeight)
                        .background(
                            color = DeathNoteTheme.colors.primary,
                            shape = DeathNoteTheme.shapes.rounded12_left
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "$index",
                        style = DeathNoteTheme.typography.itemCardIndex,
                        color = DeathNoteTheme.colors.regular
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .heightIn(min = 80.dp)
                        .background(
                            color = DeathNoteTheme.colors.regularBackground
                        )
                        .padding(horizontal = 15.dp)
                        .onSizeChanged {
                            containerHeight =
                                with(currentDensity) { it.height.toDp() }
                        },
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(
                                end = 10.dp,
                                top = 10.dp,
                                bottom = 10.dp
                            ),
                        text = "${it.surname.uppercase()}\n${it.name} ${it.patronymic}",
                        style = DeathNoteTheme.typography.itemCardTitle,
                        color = DeathNoteTheme.colors.inverse,
                        overflow = TextOverflow.Visible
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.pencil),
                        contentDescription = "edit_pencil",
                        tint = DeathNoteTheme.colors.primary,
                        modifier = Modifier
                            .pointerInput(Unit) {
                                detectTapGestures { offset ->
                                    onEvent(StudentUIEvent.SelectStudent(it))
                                    onEvent(
                                        StudentUIEvent.ChangeDialogTitle(
                                            title = R.string.edit_student
                                        )
                                    )
                                    onEvent(StudentUIEvent.ChangeDialogState(true))
                                }
                            }
                    )
                }
            }
        }
    }
}

