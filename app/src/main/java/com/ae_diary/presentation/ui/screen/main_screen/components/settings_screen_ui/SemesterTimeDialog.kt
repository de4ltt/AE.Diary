package com.ae_diary.presentation.ui.screen.main_screen.components.settings_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.ae_diary.R
import com.ae_diary.presentation.ui.theme.SexyGray
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun SemesterTimeDialog(
    onDismissRequest: () -> Unit,
    onConfirmRequest: () -> Unit
) {
    Dialog(onDismissRequest = onDismissRequest) {
        Column(
            modifier = Modifier
                .clip(DeathNoteTheme.shapes.rounded12)
                .background(DeathNoteTheme.colors.baseBackground)
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = stringResource(id = R.string.delete_semester_period),
                style = DeathNoteTheme.typography.settingsScreenItemTitle,
                color = DeathNoteTheme.colors.inverse
            )
            Row(
                modifier = Modifier.wrapContentSize(),
                horizontalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                DialogButton(
                    text = R.string.cancel,
                    color = SexyGray,
                    onClick = onDismissRequest,
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                )
                DialogButton(
                    text = R.string.ok,
                    color = DeathNoteTheme.colors.primary,
                    onClick = onConfirmRequest,
                    modifier = Modifier
                        .height(50.dp)
                        .weight(1f)
                )
            }
        }
    }
}