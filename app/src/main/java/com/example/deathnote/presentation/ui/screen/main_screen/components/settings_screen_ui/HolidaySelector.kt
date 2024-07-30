package com.example.deathnote.presentation.ui.screen.main_screen.components.settings_screen_ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.deathnote.R
import com.example.deathnote.presentation.model.event.TimetableUIEvent
import com.example.deathnote.presentation.model.state.TimetableUIState
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme
import com.example.deathnote.presentation.util.toDayOfWeek

@Composable
fun HolidaySelector(
    state: TimetableUIState,
    onEvent: (TimetableUIEvent) -> Unit
) {
    Column(modifier = Modifier.wrapContentSize()) {
        Text(
            text = stringResource(id = R.string.holidays),
            style = DeathNoteTheme.typography.textFieldTitle,
            color = DeathNoteTheme.colors.inverse
        )
        LazyRow(
            modifier = Modifier.fillMaxWidth().wrapContentHeight(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(7) { day ->
                DayBox(day = day.toDayOfWeek(), state = state, onEvent = onEvent)
            }
        }
    }
}