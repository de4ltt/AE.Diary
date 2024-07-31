package com.example.deathnote.presentation.ui.screen.main_screen.components.certificates_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.event.CertificateUIEvent
import com.example.deathnote.presentation.ui.theme.SoftGray
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.ui.theme.util.adjust
import com.example.deathnote.presentation.ui.theme.util.isDarkMode

@Composable
fun SelectStudentField(
    isActive: Boolean = true,
    studentNavigate: () -> Unit,
    student: Student,
    onEvent: (CertificateUIEvent) -> Unit
) {

    Column {

        Text(
            text = stringResource(id = R.string.student),
            style = DeathNoteTheme.typography.textFieldTitle,
            color = DeathNoteTheme.colors.inverse
        )

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(
                    shape = DeathNoteTheme.shapes.rounded12
                )
                .background(
                    color = DeathNoteTheme.colors.baseBackground
                )
                .clickable(
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() },
                    onClick = {
                        if (isActive)
                            onEvent(CertificateUIEvent.ChangeStudentSheetState(true))
                        else studentNavigate()
                    }
                ),
            contentAlignment = Alignment.CenterStart
        ) {

            if (student.name != "")
                Text(
                    text = student.getShortName(),
                    modifier = Modifier.padding(horizontal = 15.dp),
                    color = DeathNoteTheme.colors.inverse,
                    fontSize = 15.sp
                )
            else {
                Text(
                    text = stringResource(id = R.string.select_student),
                    modifier = Modifier.padding(horizontal = 15.dp),
                    color = SoftGray.adjust(if (isDarkMode()) 0.6f else 1f),
                    fontSize = 15.sp
                )
            }
        }
    }
}