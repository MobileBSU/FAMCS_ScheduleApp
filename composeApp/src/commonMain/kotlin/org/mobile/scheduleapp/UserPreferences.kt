package org.mobile.scheduleapp

import com.russhwolf.settings.Settings


class UserPreferences(private val settings: Settings) {
    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
    }

    var isLoggedIn: Boolean
        get() = settings.getBoolean(KEY_IS_LOGGED_IN, false)
        set(value) = settings.putBoolean(KEY_IS_LOGGED_IN, value)
}