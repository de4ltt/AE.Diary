package com.example.deathnote.presentation.ui.screen.main_screen.components.certificates_screen_ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.unit.dp
import com.example.deathnote.presentation.model.Certificate
import com.example.deathnote.presentation.model.Student
import com.example.deathnote.presentation.model.event.CertificateUIEvent
import com.example.deathnote.presentation.ui.cross_screen_ui.SwipeToDeleteContainer
import com.example.deathnote.presentation.ui.theme.SexyGray
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.util.getNumberOfDays


@Composable
fun CertificatePane(
    certificate: Certificate,
    student: Student,
    onEvent: (CertificateUIEvent) -> Unit
) {

    Box(
        modifier = Modifier.wrapContentSize().clip(DeathNoteTheme.shapes.rounded12)
    ) {
        SwipeToDeleteContainer(
            item = certificate,
            onDelete = { onEvent(CertificateUIEvent.DeleteCertificate(certificate)) }
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
                            text = student.getShortName(),
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
    }
}