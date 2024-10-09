package com.ae_diary.presentation.ui.screen.main_screen.components.settings_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.ae_diary.R
import com.ae_diary.presentation.ui.common.bottom_sheet.SettingsBottomButton
import com.ae_diary.presentation.ui.theme.SexyGray
import com.ae_diary.presentation.ui.theme.White
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.ae_diary.presentation.ui.theme.util.isDarkMode

@Composable
fun DismissAcceptButton(
    isActive: Boolean = true,
    onDismissRequest: () -> Unit,
    onAcceptRequest: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        horizontalArrangement = Arrangement.spacedBy(15.dp)
    ) {

        Box(
            modifier = Modifier
                .height(50.dp)
                .aspectRatio(1f)
                .clip(
                    shape = DeathNoteTheme.shapes.rounded12
                )
                .background(
                    color = if (isDarkMode()) DeathNoteTheme.colors.baseBackground else SexyGray
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onDismissRequest
                ),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = R.drawable.disagree),
                contentDescription = "icon",
                tint = White,
                modifier = Modifier.size(25.dp)
            )
        }

        SettingsBottomButton(
            title = R.string.ready,
            onClickAction = onAcceptRequest,
            isActive = isActive
        )

    }
}