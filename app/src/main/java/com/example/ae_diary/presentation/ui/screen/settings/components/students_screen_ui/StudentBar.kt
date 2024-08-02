package com.example.ae_diary.presentation.ui.screen.settings.components.students_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ae_diary.R
import com.example.ae_diary.presentation.model.Student
import com.example.ae_diary.presentation.model.event.StudentUIEvent
import com.example.ae_diary.presentation.ui.cross_screen_ui.delete_container.SwipeToDeleteContainer
import com.example.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun StudentBar(
    index: Int,
    student: Student,
    onEvent: (StudentUIEvent) -> Unit
) {

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
                        .padding(horizontal = 15.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .weight(1f)
                            .padding(end = 10.dp),
                        text = "${it.surname}\n${it.name} ${it.patronymic}",
                        style = DeathNoteTheme.typography.itemCardTitle,
                        color = DeathNoteTheme.colors.inverse
                    )

                    Icon(
                        painter = painterResource(id = R.drawable.pencil),
                        contentDescription = "edit_pencil",
                        tint = DeathNoteTheme.colors.primary,
                        modifier = Modifier
                            .pointerInput(Unit) {
                                detectTapGestures { offset ->
                                    onEvent(
                                        StudentUIEvent.ChangeDialogTitle(
                                            title = R.string.edit_student
                                        )
                                    )
                                    onEvent(StudentUIEvent.SelectStudent(it))
                                    onEvent(StudentUIEvent.ChangeDialogState(true))
                                }
                            }
                    )
                }
            }
        }
    }
}

