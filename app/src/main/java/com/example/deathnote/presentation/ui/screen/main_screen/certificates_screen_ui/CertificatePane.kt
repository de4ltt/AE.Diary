package com.example.deathnote.presentation.ui.screen.main_screen.certificates_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.model.Certificate
import com.example.deathnote.presentation.model.util.getNumberOfDays
import com.example.deathnote.presentation.ui.theme.SexyGray
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.ramcosta.composedestinations.annotation.Destination


@Composable
fun CertificatePane(
    certificate: Certificate,
    onClick: () -> Unit
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                elevation = 4.dp,
                shape = DeathNoteTheme.shapes.rounded12,
                ambientColor = DeathNoteTheme.colors.regularBackground,
                clip = false
            )
            .clip(
                shape = DeathNoteTheme.shapes.rounded12
            )
            .background(
                color = DeathNoteTheme.colors.regularBackground
            )
            .clickable(
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {

        certificate.apply {

            Box(
                modifier = Modifier
                    .padding(15.dp)
                    .height(65.dp)
                    .aspectRatio(1f)
                    .clip(
                        shape = CircleShape
                    )
                    .background(
                        color = DeathNoteTheme.colors.primaryBackground
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "${getNumberOfDays()}",
                    style = DeathNoteTheme.typography.topBar,
                    color = SexyGray
                )
            }

            Column {

                Text(
                    text = "Болошко Н.А.",
                    style = DeathNoteTheme.typography.settingsScreenItemTitle,
                    color = DeathNoteTheme.colors.inverse
                )

                Text(
                    text = "$start - $end",
                    style = DeathNoteTheme.typography.settingsScreenItemSubtitle,
                    color = DeathNoteTheme.colors.inverse
                )
            }
        }
    }
}