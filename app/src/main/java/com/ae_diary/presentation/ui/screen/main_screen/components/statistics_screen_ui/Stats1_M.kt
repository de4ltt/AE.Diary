package com.ae_diary.presentation.ui.screen.main_screen.components.statistics_screen_ui

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.basicMarquee
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.ae_diary.R
import com.ae_diary.presentation.model.Subject
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Stats1_M(
    titled: Boolean = false,
    subject: Subject,
    respectfulAbsences: Int = 0,
    absences: Int = 0,
    absencePercent: Int = 0
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Column(
            modifier = Modifier
                .wrapContentHeight()
                .weight(1f)
        ) {
            if (titled)
                Text(
                    text = stringResource(id = R.string.subject),
                    style = DeathNoteTheme.typography.textFieldTitle,
                    color = DeathNoteTheme.colors.inverse
                )

            Box(
                modifier = Modifier
                    .height(50.dp)
                    .fillMaxWidth()
                    .clip(
                        shape = DeathNoteTheme.shapes.rounded12
                    )
                    .background(DeathNoteTheme.colors.regularBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = subject.getShortName() + " (${stringResource(id = if (subject.type == "lk") R.string.lk else R.string.pr)})",
                    style = DeathNoteTheme.typography.settingsScreenItemTitle,
                    color = DeathNoteTheme.colors.inverse,
                    fontStyle = FontStyle.Normal,
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .basicMarquee()
                )
            }
        }

        Column {
            if (titled)
                Text(
                    text = stringResource(id = R.string.absence),
                    style = DeathNoteTheme.typography.textFieldTitle,
                    color = DeathNoteTheme.colors.inverse
                )

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(
                        shape = DeathNoteTheme.shapes.rounded12
                    )
                    .background(DeathNoteTheme.colors.regularBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$absences",
                    style = DeathNoteTheme.typography.statisticsScreenNumbers,
                    color = DeathNoteTheme.colors.inverse,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        Column {
            if (titled)
                Text(
                    text = stringResource(id = R.string.res),
                    style = DeathNoteTheme.typography.textFieldTitle,
                    color = DeathNoteTheme.colors.inverse
                )

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(
                        shape = DeathNoteTheme.shapes.rounded12
                    )
                    .background(DeathNoteTheme.colors.regularBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$respectfulAbsences",
                    style = DeathNoteTheme.typography.statisticsScreenNumbers,
                    color = DeathNoteTheme.colors.inverse,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal
                )
            }
        }

        Column {
            if (titled)
                Text(
                    text = stringResource(id = R.string.abs_per),
                    style = DeathNoteTheme.typography.textFieldTitle,
                    color = DeathNoteTheme.colors.primary
                )

            Box(
                modifier = Modifier
                    .size(50.dp)
                    .clip(
                        shape = DeathNoteTheme.shapes.rounded12
                    )
                    .background(DeathNoteTheme.colors.regularBackground),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "$absencePercent",
                    style = DeathNoteTheme.typography.statisticsScreenNumbers,
                    color = DeathNoteTheme.colors.inverse,
                    fontStyle = FontStyle.Normal,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}