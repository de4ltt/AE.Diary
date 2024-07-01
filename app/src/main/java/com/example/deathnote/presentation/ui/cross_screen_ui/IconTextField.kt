package com.example.deathnote.presentation.ui.cross_screen_ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

/*
@Composable
fun IconTextField(
    modifier: Modifier,
    @DrawableRes icon: Int?,
    hint: String,
    maxLines: Int = 1,
    isTextCentered: Boolean = false,
    textValue: String,
    onTextValueChange: (String) -> Unit
) {
    var _textValue by remember {
        mutableStateOf(textValue)
    }


    BasicTextField(
        modifier = modifier
            .background(
                ExtendedTheme.colors.profileBar,
                RoundedCornerShape(12.dp)),
        value = textValue,
        onValueChange = {
            _textValue = it
            onTextValueChange(it)
        },
        textStyle = if (isTextCentered) TextStyle(textAlign = TextAlign.Center) else TextStyle(
            textAlign = TextAlign.Start
        ),
        maxLines = maxLines
    ) { textField ->

        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {

            icon?.let {
                Column(
                    modifier = Modifier.width(60.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {

                    Icon(
                        painter = painterResource(id = it),
                        contentDescription = "icon",
                        tint = ExtendedTheme.colors.redAccent
                    )
                }
            }

            if (icon == null)
                Spacer(modifier = Modifier.padding(start = 20.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 20.dp)
            ) {
                if (textValue.isEmpty())
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = hint,
                        fontWeight = FontWeight.W400,
                        color = Color.Gray,
                        textAlign = if (isTextCentered) TextAlign.Center else TextAlign.Start
                    )
                textField()
            }
        }
    }

}*/
