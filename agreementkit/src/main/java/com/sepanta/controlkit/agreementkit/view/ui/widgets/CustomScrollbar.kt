package com.sepanta.controlkit.agreementkit.view.ui.widgets/*
 *  File: CustomScrollbar.kt
 *
 *  Created by morteza on 9/9/25.
 */
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.gestures.detectVerticalDragGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.text.BasicText
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.BoxWithConstraints
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun CustomScrollbar( modifier: Modifier,textView: @Composable () -> Unit ,color: Color? = null,) {
    val scrollState = rememberScrollState()
    var showScrollbar by remember { mutableStateOf(false) }

    val alpha by animateFloatAsState(
        targetValue = if (showScrollbar) 1f else 0f,
        animationSpec = tween(durationMillis = 300)
    )

    val density = LocalDensity.current
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(scrollState.value) {
        showScrollbar = true
        delay(1000)
        showScrollbar = false
    }

    BoxWithConstraints(modifier = modifier) {
        val viewportHeightPx = with(density) { maxHeight.toPx() }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(end = 12.dp)
        ) {
            textView()

        }

        if (scrollState.maxValue > 0) {
            val scrollbarHeight = (viewportHeightPx / (scrollState.maxValue + viewportHeightPx)) * viewportHeightPx
            val scrollbarOffset = (scrollState.value.toFloat() / scrollState.maxValue) * (viewportHeightPx - scrollbarHeight)

            Canvas(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(12.dp)
                    .align(Alignment.CenterEnd)
                    .alpha(alpha)
                    .pointerInput(Unit) {
                        detectVerticalDragGestures { change, dragAmount ->
                            change.consume()
                            val proportion = dragAmount / viewportHeightPx
                            val newValue =
                                (scrollState.maxValue * proportion + scrollState.value).coerceIn(0f, scrollState.maxValue.toFloat())

                            coroutineScope.launch {
                                scrollState.scrollTo(newValue.toInt())
                            }
                        }
                    }
            ) {
                drawRoundRect(
                    color =color?: Color.Gray.copy(alpha = 0.6f),
                    topLeft = Offset(0f, scrollbarOffset),
                    size = Size(width = size.width, height = scrollbarHeight.coerceAtLeast(40f)),
                    cornerRadius = CornerRadius(6f, 6f)
                )
            }
        }
    }
}
