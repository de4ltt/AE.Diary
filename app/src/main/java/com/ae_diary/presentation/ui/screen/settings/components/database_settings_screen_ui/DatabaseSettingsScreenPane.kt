package com.ae_diary.presentation.ui.screen.settings.components.database_settings_screen_ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.ae_diary.presentation.ui.common.bounceClick
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@Composable
@Stable
fun DatabaseSettingsScreenPane(
    @StringRes titleId: Int,
    @DrawableRes iconId: Int,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .bounceClick(onClick = onClick)
            .shadow(
                elevation = 4.dp,
                shape = DeathNoteTheme.shapes.rounded12,
                ambientColor = DeathNoteTheme.colors.regularBackground
            )
            .background(
                color = DeathNoteTheme.colors.regularBackground,
                shape = DeathNoteTheme.shapes.rounded12
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .padding(15.dp)
                .size(50.dp)
                .clip(CircleShape)
                .background(color = DeathNoteTheme.colors.primaryBackground),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = iconId),
                tint = DeathNoteTheme.colors.primary,
                contentDescription = "icon",
                modifier = Modifier.size(20.dp)
            )
        }

        Text(
            text = stringResource(id = titleId),
            style = DeathNoteTheme.typography.settingsScreenItemTitle,
            color = DeathNoteTheme.colors.inverse
        )

    }
}