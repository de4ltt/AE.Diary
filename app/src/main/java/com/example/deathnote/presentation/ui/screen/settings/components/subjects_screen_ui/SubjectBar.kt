package com.example.deathnote.presentation.ui.screen.settings.components.subjects_screen_ui

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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Subject
import com.example.deathnote.presentation.model.event.SubjectUIEvent
import com.example.deathnote.presentation.ui.cross_screen_ui.delete_container.SwipeToDeleteContainer
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun SubjectBar(
    index: Int,
    subject: Subject,
    onEvent: (SubjectUIEvent) -> Unit
) {

    Row(
        modifier = Modifier
            .clip(DeathNoteTheme.shapes.rounded12)
    ) {
        SwipeToDeleteContainer(
            item = subject,
            onDelete = {
                onEvent(SubjectUIEvent.DeleteSubject(subject))
                onEvent(SubjectUIEvent.SelectSubject(Subject()))
            },
            icon = R.drawable.church,
            content = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                        .heightIn(min = 80.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Box(
                        modifier = Modifier
                            .width(28.dp)
                            .heightIn(min = 80.dp)
                            .background(
                                color = DeathNoteTheme.colors.primary
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
                            text = "${subject.name} (${
                                if (subject.type == "lk") stringResource(
                                    id = R.string.lk
                                )
                                else stringResource(id = R.string.pr)
                            })",
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
                                        onEvent(SubjectUIEvent.ChangeDialogTitle(R.string.edit_subject))
                                        onEvent(SubjectUIEvent.SelectSubject(it))
                                        onEvent(SubjectUIEvent.ChangeDialogState(true))
                                    }
                                }
                        )
                    }
                }
            }
        )
    }
}

