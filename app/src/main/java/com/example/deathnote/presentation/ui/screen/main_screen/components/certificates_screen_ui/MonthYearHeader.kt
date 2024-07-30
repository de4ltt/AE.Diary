package com.example.deathnote.presentation.ui.screen.main_screen.components.certificates_screen_ui

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.deathnote.presentation.model.Certificate
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.util.toStringResMonth
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun MonthYearHeader(items: List<Certificate>) {
    val dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
    val firstItemDate = LocalDate.parse(items[0].start, dateFormatter)
    Text(
        text = stringResource(id = firstItemDate.month.toStringResMonth()) + " " + firstItemDate.year.toString(),
        style = DeathNoteTheme.typography.settingsScreenItemSubtitle,
        color = DeathNoteTheme.colors.inverse
    )
}