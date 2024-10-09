package com.ae_diary.presentation.ui.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun ItemGrid(
    modifier: Modifier = Modifier,
    contentSpacing: Dp = 10.dp,
    contentPadding: Dp = 40.dp,
    items: List<@Composable () -> Unit>? = null
) {

    Box(
        modifier = modifier
    ) {

        Column(
            modifier = Modifier.padding(contentPadding).fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(contentSpacing),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            items?.let { list ->

                var index = 0

                while (index < list.lastIndex) {

                    Row(
                        modifier = Modifier.weight(1f),
                        horizontalArrangement = Arrangement.spacedBy(contentSpacing)
                    ) {

                        Box(
                            modifier = Modifier.weight(1f),
                            content = {
                                items[index].invoke()
                            },
                            contentAlignment = Alignment.Center
                        )

                        items.getOrNull(index + 1)?.let {
                            Box(
                                modifier = Modifier.weight(1f),
                                content = {
                                    it()
                                },
                                contentAlignment = Alignment.Center
                            )
                        }

                    }

                    index += 2
                }
            }
        }
    }
}