package org.mobile.scheduleapp.screens.searchScreens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color

@Composable
fun Divider(
    modifier: Modifier = Modifier,
    color: Color) {
    Canvas(modifier = modifier.fillMaxWidth()) {
        drawLine(
            color = color,
            start = Offset(0f, 0f),
            end = Offset(size.width, 0f)
        )
    }
}