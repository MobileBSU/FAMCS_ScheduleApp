package org.mobile.scheduleapp.components

import androidx.compose.foundation.clickable
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter

@Composable
fun BackButton(
    icon: Painter,
    modifier: Modifier = Modifier,
    onNavigateClick: () -> Unit
) {
    Icon(
        painter = icon,
        contentDescription = "Back Button",
        tint = MaterialTheme.colorScheme.primary,
        modifier = modifier
            .clickable { onNavigateClick() }
    )
}