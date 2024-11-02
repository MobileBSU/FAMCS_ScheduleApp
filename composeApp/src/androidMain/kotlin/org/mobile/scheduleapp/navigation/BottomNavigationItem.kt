package org.mobile.scheduleapp.navigation

import android.media.Image
import androidx.compose.ui.graphics.painter.Painter

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
    val route: String
)
