package com.example.deathnote.presentation.ui.cross_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.navigation.getSettingsTopBarIcon
import com.example.deathnote.presentation.navigation.getSettingsTopBarLabel
import com.example.deathnote.presentation.ui.theme.SexyGray
import com.example.deathnote.presentation.ui.theme.SoftGray
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme


@Composable
fun DarkTopBar(
    destination: AppDestination,
    onIconClick: () -> Unit = {}
) {

    Box(
        Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .clip(
                shape = DeathNoteTheme.shapes.rounded_bot_strt_mini
            )
            .background(
                color = SexyGray
            ), Alignment.Center
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 60.dp,
                    bottom = 20.dp,
                    start = 25.dp,
                    end = 25.dp
                )
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            destination.getSettingsTopBarLabel()?.let {
                Text(
                    text = stringResource(id = it),
                    style = DeathNoteTheme.typography.topBar,
                    color = SoftGray
                )
            }

            destination.getSettingsTopBarIcon()?.let {
                Icon(
                    painter = painterResource(id = it),
                    contentDescription = "icon",
                    tint = SoftGray,
                    modifier = Modifier
                        .size(25.dp)
                        .clickable(
                        onClick = onIconClick,
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )
                )
            }
        }
    }
}