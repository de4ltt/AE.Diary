package com.example.ae_diary.presentation.ui.common.top_bar

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.ae_diary.presentation.navigation.AppDestination
import com.example.ae_diary.presentation.navigation.getSettingsTopBarIcon
import com.example.ae_diary.presentation.navigation.getSettingsTopBarLabel
import com.example.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun SettingsTopBar(
    destination: AppDestination,
    onIconClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .wrapContentSize()
            .background(color = animateColorAsState(
                targetValue = DeathNoteTheme.colors.baseBackground).value
            )
            .animateContentSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        destination.getSettingsTopBarIcon()?.let {
            Icon(
                painter = painterResource(id = it),
                contentDescription = "arrow_left",
                modifier = Modifier
                    .size(40.dp)
                    .pointerInput(Unit) {
                        detectTapGestures {
                            onIconClick()
                        }
                    },
                tint = animateColorAsState(targetValue = DeathNoteTheme.colors.inverse).value
            )
        }

        destination.getSettingsTopBarLabel()?.let {
            Text(
                text = stringResource(id = it),
                style = DeathNoteTheme.typography.topBar,
                color = animateColorAsState(targetValue = DeathNoteTheme.colors.inverse).value
            )
        }

    }
}