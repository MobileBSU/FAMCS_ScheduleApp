package org.mobile.scheduleapp.presentation.view.navigation.bottomBar

import androidx.compose.ui.graphics.painter.Painter

data class BottomNavigationItem(
    val title: String,
    val selectedIcon: Painter,
    val unselectedIcon: Painter,
    val route: String
)
