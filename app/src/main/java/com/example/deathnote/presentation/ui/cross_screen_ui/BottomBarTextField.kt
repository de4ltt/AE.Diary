package com.example.deathnote.presentation.ui.cross_screen_ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deathnote.R
import com.example.deathnote.presentation.ui.theme.SoftGray
import com.example.deathnote.presentation.ui.theme.settings.DeathNoteTheme

@Composable
fun BottomBarTextField(
    @StringRes title: Int,
    onValueChange: (String) -> Unit,
    @DrawableRes icon: Int? = null,
    value: String,
    isDatePicker: Boolean = false,
    isCentered: Boolean = true,
    isStartDate: Boolean = false,
    previousDate: String = ""
) {

    var isDataPickerDialog by remember {
        mutableStateOf(false)
    }

    Column{

        Text(
            text = stringResource(id = title),
            style = DeathNoteTheme.typography.textFieldTitle,
            color = DeathNoteTheme.colors.inverse
        )

        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .clip(
                    shape = DeathNoteTheme.shapes.rounded12
                )
                .background(
                    color = DeathNoteTheme.colors.baseBackground
                ),
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            maxLines = 1,
            enabled = !isDatePicker,
            textStyle = TextStyle(
                color = DeathNoteTheme.colors.inverse,
                fontSize = 15.sp,
                textAlign = if (isCentered) TextAlign.Center else TextAlign.Start
            )
        ) { textField ->
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 15.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                content = {
                    icon?.let {
                        Icon(
                            painter = painterResource(id = it),
                            contentDescription = "icon",
                            modifier = Modifier.padding(start = 15.dp).size(20.dp),
                            tint = DeathNoteTheme.colors.primary
                        )
                    }

                    if (!isDatePicker) {
                        Box() {
                            if (value.isEmpty()) {
                                Text(
                                    color = SoftGray,
                                    fontSize = 15.sp,
                                    textAlign = TextAlign.Start,
                                    text = stringResource(id = R.string.name_sur_pat)
                                )
                            }
                            textField()
                        }
                    }
                    else Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .clickable(
                                interactionSource = remember { MutableInteractionSource() },
                                indication = null,
                                onClick = {
                                    isDataPickerDialog = !isDataPickerDialog
                                }
                            ),
                        contentAlignment = Alignment.Center,
                        content = { textField() }
                    )
                }
            )
        }
    }

    if (isDataPickerDialog)
        DatePickerWithDialog(
            isPreviousDate = isStartDate,
            previousDate = previousDate,
            curDate = value,
            onAcceptRequest = {
                onValueChange(it)
                isDataPickerDialog = false
            },
            onDismissRequest = { isDataPickerDialog = !isDataPickerDialog }
        )
}