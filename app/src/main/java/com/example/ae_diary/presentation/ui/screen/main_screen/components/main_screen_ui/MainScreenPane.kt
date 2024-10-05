package com.example.ae_diary.presentation.ui.screen.main_screen.components.main_screen_ui

import android.util.Log
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.toSize
import com.example.ae_diary.presentation.ui.common.bounceClick
import com.example.ae_diary.presentation.ui.theme.SexyGray
import com.example.ae_diary.presentation.ui.theme.settings.DeathNoteTheme
import com.example.ae_diary.presentation.ui.theme.util.isDarkMode
import com.example.ae_diary.presentation.util.toPx

@Composable
fun MainScreenPane(
    @DrawableRes topStartIcon: Int,
    @DrawableRes middleEndIcon: Int,
    @StringRes title: Int,
    onClick: () -> Unit = { },
    onSizeChange: (Boolean) -> Unit = {},
    isReduced: Boolean = false
) {

    val iconSizeFloat = 40.dp.toPx()

    var isTextOverflow by remember {
        mutableStateOf(false)
    }

    val animationDuration = 500

    val innerColor = if (!isDarkMode()) SexyGray else DeathNoteTheme.colors.inverse

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .bounceClick()
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
            )
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() },
                onClick = onClick
            )
            .onSizeChanged {
                onSizeChange(it.toSize().height < 5 * iconSizeFloat || isTextOverflow)

                Log.d("HEIGHT", "${it.toSize().height}\n$iconSizeFloat\n$isTextOverflow")
            }
    ) {
        AnimatedContent(
            targetState = isReduced,
            transitionSpec = {
                (fadeIn(tween(animationDuration)) + scaleIn(
                    tween(
                        animationDuration
                    )
                )).togetherWith(
                    scaleOut(tween(animationDuration)) + fadeOut(
                        tween(
                            animationDuration
                        )
                    )
                )
            },
            label = ""
        ) {

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        start = 15.dp,
                        bottom = 15.dp,
                        top = 15.dp,
                        end = animateDpAsState(targetValue = if (isReduced) 15.dp else 0.dp).value
                    )
                    .animateContentSize(tween(animationDuration)),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                if (it) {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            painter = painterResource(id = topStartIcon),
                            contentDescription = "topStartIcon",
                            modifier = Modifier
                                .size(40.dp),
                            tint = innerColor
                        )
                    }
                } else {
                    Icon(
                        painter = painterResource(id = topStartIcon),
                        contentDescription = "topStartIcon",
                        modifier = Modifier.size(30.dp),
                        tint = innerColor
                    )

                    Icon(
                        painter = painterResource(id = middleEndIcon),
                        contentDescription = "middleEndIcon",
                        modifier = Modifier
                            .size(100.dp)
                            .align(Alignment.End)
                            .offset(x = 25.dp),
                        tint = innerColor
                    )

                    Text(
                        text = stringResource(id = title),
                        color = innerColor,
                        fontSize = 19.sp,
                        fontStyle = FontStyle.Italic,
                        onTextLayout = {
                            isTextOverflow = it.didOverflowHeight
                        }
                    )
                }
            }
        }
    }
}