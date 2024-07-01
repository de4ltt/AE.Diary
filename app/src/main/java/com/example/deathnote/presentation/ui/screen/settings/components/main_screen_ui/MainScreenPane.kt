package com.example.deathnote.presentation.ui.screen.settings.components.main_screen_ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun MainScreenPane(
    @DrawableRes topStartIcon: Int,
    @DrawableRes middleEndIcon: Int,
    @StringRes title: Int,
    onClick: () -> Unit = { }
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .shadow(
                elevation = 4.dp,
                shape = DeathNoteTheme.shapes.rounded12,
                ambientColor = DeathNoteTheme.colors.regularBackground,
                clip = false
            )
            .clip(shape = DeathNoteTheme.shapes.rounded12)
            .background(
                color = DeathNoteTheme.colors.regularBackground
            )
            .clickable(
                onClick = onClick
            )
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    start = 15.dp,
                    bottom = 15.dp,
                    top = 15.dp
                ),
            verticalArrangement = Arrangement.spacedBy(5.dp)
        ) {
            Icon(
                painter = painterResource(id = topStartIcon),
                contentDescription = "topStartIcon",
                modifier = Modifier.size(30.dp),
                tint = DeathNoteTheme.colors.inverse
            )

            Icon(
                painter = painterResource(id = middleEndIcon),
                contentDescription = "middleEndIcon",
                modifier = Modifier.size(100.dp)
                    .align(Alignment.End)
                    .offset(x = 25.dp),
                tint = DeathNoteTheme.colors.inverse
            )

            Text(
                text = stringResource(id = title),
                color = DeathNoteTheme.colors.inverse,
                fontSize = 19.sp,
                fontStyle = FontStyle.Italic
            )
        }
    }
}