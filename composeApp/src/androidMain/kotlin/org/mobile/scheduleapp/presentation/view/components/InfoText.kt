package org.mobile.scheduleapp.screens.searchScreens

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.mobile.scheduleapp.presentation.view.theming.NeutralDarkLight

@Composable
fun InfoText(text: String) {
    Text(
        text = text,
        style = MaterialTheme.typography.bodySmall,
        color = NeutralDarkLight,
    )
}