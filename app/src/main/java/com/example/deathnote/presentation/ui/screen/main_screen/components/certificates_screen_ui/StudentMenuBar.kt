package com.example.deathnote.presentation.ui.screen.main_screen.components.certificates_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.util.getFullName

@Composable
fun StudentMenuBar(
    student: Student,
    onSelect: (Student) -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(
                min = 60.dp,
                max = 80.dp
            )
            .wrapContentHeight()
            .shadow(
                elevation = 4.dp,
                shape = DeathNoteTheme.shapes.rounded12,
                ambientColor = DeathNoteTheme.colors.regularBackground
            )
            .background(DeathNoteTheme.colors.regularBackground)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = { onSelect(student) }
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxHeight()
                .width(28.dp)
                .background(
                    color = DeathNoteTheme.colors.primary
                )
        )

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(end = 10.dp),
            text = student.getFullName(),
            style = DeathNoteTheme.typography.itemCardTitle,
            color = DeathNoteTheme.colors.inverse
        )
    }
}