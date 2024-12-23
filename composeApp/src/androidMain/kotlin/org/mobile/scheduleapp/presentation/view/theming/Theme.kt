package org.mobile.scheduleapp.presentation.view.theming

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat


val lightColorsScheme = lightColorScheme(
    primary = HighlightDarkest,
    onPrimary = NeutralLightWhite,
    background = NeutralLightLightest,
    onBackground = NeutralDarkDark,
    surface = NeutralLightLight,
    onSurface = NeutralDarkLight,
    secondary = HighlightLight,
    onSecondary = NeutralDarkDarkest,
    error = ErrorRedMedium,
    onError = NeutralLightWhite
)

val darkColorScheme = darkColorScheme(
    primary = HighlightMedium,
    onPrimary = NeutralDarkDarkest,
    background = NeutralDarkDark,
    onBackground = NeutralLightWhite,
    surface = NeutralDarkMedium,
    onSurface = NeutralLightLight,
    secondary = HighlightDark,
    onSecondary = NeutralLightLightest,
    error = ErrorRedMedium,
    onError = NeutralLightWhite
)

@Composable
fun ScheduleAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) darkColorScheme else lightColorsScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.background.toArgb()
            window.navigationBarColor = colorScheme.background.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
        shapes = Shapes
    )
}