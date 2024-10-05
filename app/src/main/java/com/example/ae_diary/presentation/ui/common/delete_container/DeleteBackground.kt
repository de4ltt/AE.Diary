package com.example.ae_diary.presentation.ui.common.delete_container

//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.DismissDirection
import androidx.compose.material.DismissState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.ae_diary.presentation.ui.theme.DarkRed
import com.example.ae_diary.presentation.ui.theme.Transparent

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DeleteBackground(
    swipeDismissState: DismissState,
    icon: Int
) {
    val color by animateColorAsState(
        targetValue = if (swipeDismissState.dismissDirection == DismissDirection.EndToStart) {
            DarkRed
        } else Transparent,
        label = ""
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = color)
            .padding(16.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Icon(
            painter = painterResource(id = icon),
            modifier = Modifier.size(20.dp),
            contentDescription = "",
            tint = androidx.compose.ui.graphics.Color.White
        )
    }
}