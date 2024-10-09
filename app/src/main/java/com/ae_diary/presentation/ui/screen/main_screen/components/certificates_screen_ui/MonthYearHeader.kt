package com.ae_diary.presentation.ui.screen.main_screen.components.certificates_screen_ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.ae_diary.presentation.model.Certificate
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.ae_diary.presentation.util.TimeFormatter.dateFormatter
import com.ae_diary.presentation.util.toStringResMonth
import java.time.LocalDate

@Composable
fun MonthYearHeader(items: List<Certificate>) {
    val firstItemDate = LocalDate.parse(items[0].start, dateFormatter)

    Text(
        text = stringResource(id = firstItemDate.month.toStringResMonth()) + " " + firstItemDate.year.toString(),
        style = DeathNoteTheme.typography.settingsScreenItemSubtitle,
        color = DeathNoteTheme.colors.inverse
    )
}