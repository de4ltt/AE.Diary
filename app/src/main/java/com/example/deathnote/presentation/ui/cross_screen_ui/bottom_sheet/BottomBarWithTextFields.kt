package com.example.deathnote.presentation.ui.cross_screen_ui.bottom_sheet

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.ui.screen.main_screen.components.settings_screen_ui.DismissAcceptButton
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomBarWithTextFields(
    content: @Composable () -> Unit,
    @StringRes title: Int,
    onAcceptRequest: () -> Unit,
    onDismissRequest: () -> Unit,
    isActive: Boolean = true
) {

    ModalBottomSheet(
        dragHandle = {},
        onDismissRequest = onDismissRequest,
        containerColor = DeathNoteTheme.colors.regularBackground,
        content = {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .padding(
                        start = 25.dp,
                        bottom = 25.dp,
                        end = 25.dp
                    ),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth().padding(top = 35.dp),
                    textAlign = TextAlign.Center,
                    text = stringResource(id = title),
                    style = DeathNoteTheme.typography.bottomSheetTitle,
                    color = DeathNoteTheme.colors.inverse
                )

                content()

                DismissAcceptButton(
                    isActive = isActive,
                    onAcceptRequest = onAcceptRequest,
                    onDismissRequest = onDismissRequest
                )
            }
        }
    )
}