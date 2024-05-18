package com.example.deathnote.presentation.ui.screen.settings.composable.settings_screen_ui

import android.view.MotionEvent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.deathnote.R

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SettingsOptionPane(
    onClick: () -> Unit = { },
    @DrawableRes icon: Int,
    @StringRes title: Int,
    @StringRes subtitle: Int
) {

    val interactionSrc = remember { MutableInteractionSource() }
    val isPressed by interactionSrc.collectIsPressedAsState()

    val color by animateColorAsState(
        targetValue = if (isPressed) MaterialTheme.colorScheme.primaryContainer else MaterialTheme.colorScheme.surfaceTint,
        label = "gradient_color",
        animationSpec = tween(150)
    )

    val gradient = Brush.linearGradient(
            colors = listOf(
                MaterialTheme.colorScheme.surfaceTint,
                MaterialTheme.colorScheme.surfaceTint,
                color
            ),
            start = Offset.Zero,
            end = Offset.Infinite
        )


    Row(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp),
                ambientColor = MaterialTheme.colorScheme.surfaceTint
            )
            .background(
                brush = gradient,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable (
                indication = null,
                interactionSource = interactionSrc,
                onClick = onClick
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .padding(15.dp)
                .size(60.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.primaryContainer),
            contentAlignment = Alignment.Center
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = "icon",
                modifier = Modifier
                    .size(30.dp),
                tint = MaterialTheme.colorScheme.primary
            )
        }

        Column(
            modifier = Modifier
                .wrapContentSize()
                .padding(end = 15.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            Text(
                text = stringResource(id = title),
                fontFamily = FontFamily(Font(R.font.inter_bold)),
                color = MaterialTheme.colorScheme.onPrimary,
                fontSize = 16.sp,
                lineHeight = 16.sp
            )

            Text(
                text = stringResource(id = subtitle),
                fontFamily = FontFamily(Font(R.font.inter_semibold)),
                color = MaterialTheme.colorScheme.onSecondary,
                fontSize = 13.sp,
                lineHeight = 13.sp
            )
        }

    }
}