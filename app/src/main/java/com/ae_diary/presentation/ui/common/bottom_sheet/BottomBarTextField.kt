package com.ae_diary.presentation.ui.common.bottom_sheet

import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ae_diary.presentation.ui.theme.SoftGray
import com.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.ae_diary.presentation.ui.theme.util.adjust
import com.ae_diary.presentation.ui.theme.util.isDarkMode

@Composable
fun BottomBarTextField(
    icon: @Composable () -> Unit = {},
    @StringRes title: Int,
    @StringRes innerTitle: Int,
    onValueChange: (String) -> Unit = {},
    onClick: () -> Unit = {},
    value: String,
    isActive: Boolean = true,
    isCentered: Boolean = true,
) {

    Column {

        Text(
            text = stringResource(id = title),
            style = DeathNoteTheme.typography.textFieldTitle,
            color = DeathNoteTheme.colors.inverse
        )

        BasicTextField(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .heightIn(min = 50.dp)
                .clip(
                    shape = DeathNoteTheme.shapes.rounded12
                )
                .background(
                    color = DeathNoteTheme.colors.baseBackground
                )
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null,
                    onClick = onClick
                ),
            value = value,
            onValueChange = {
                onValueChange(it)
            },
            enabled = isActive,
            textStyle = TextStyle(
                color = DeathNoteTheme.colors.inverse,
                fontSize = 15.sp,
                textAlign = if (isCentered) TextAlign.Center else TextAlign.Start
            )
        ) { textField ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(horizontal = 15.dp, vertical = 15.dp)
                    .animateContentSize(tween(300)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(15.dp),
                content = {
                    icon()

                    Box {
                        if (value.isEmpty()) {
                            Text(
                                color = SoftGray.adjust(if (isDarkMode()) 0.6f else 1f),
                                fontSize = 15.sp,
                                textAlign = TextAlign.Start,
                                text = stringResource(id = innerTitle)
                            )
                        }
                        textField()
                    }
                }
            )
        }
    }
}