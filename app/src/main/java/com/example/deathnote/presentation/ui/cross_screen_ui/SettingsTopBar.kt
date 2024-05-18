package com.example.deathnote.presentation.ui.cross_screen_ui

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deathnote.R
import com.example.deathnote.presentation.navigation.AppDestination
import com.example.deathnote.presentation.navigation.getSettingsTopBarIcon
import com.example.deathnote.presentation.navigation.getSettingsTopBarLabel

@Composable
fun SettingsTopBar(
    destination: AppDestination,
    onIconClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .wrapContentSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {

        Icon(
            painter = painterResource(id = destination.getSettingsTopBarIcon()),
            contentDescription = "arrow_left",
            modifier = Modifier
                .size(40.dp)
                .pointerInput(Unit) {
                    detectTapGestures {
                        onIconClick()
                    }
                }
        )

        Text(
            text = stringResource(id = destination.getSettingsTopBarLabel()),
            fontSize = 27.sp,
            fontFamily = FontFamily(Font(R.font.inter_bold))
        )
    }
}