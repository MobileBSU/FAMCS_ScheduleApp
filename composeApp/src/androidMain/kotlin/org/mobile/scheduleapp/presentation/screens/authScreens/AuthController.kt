package org.mobile.scheduleapp.presentation.screens.authScreens

import androidx.compose.runtime.Stable

@Stable
interface AuthController {
    fun setUserLoggedIn(loggedIn: Boolean)
}