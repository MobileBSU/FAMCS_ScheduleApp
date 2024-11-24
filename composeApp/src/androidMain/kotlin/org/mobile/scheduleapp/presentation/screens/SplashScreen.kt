package org.mobile.scheduleapp.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import kotlinx.coroutines.delay
import org.mobile.scheduleapp.presentation.view.navigation.AppRoute

@Composable
fun SplashScreen(
    navController: NavController,
    token: String?
) {
    LaunchedEffect(key1 = token) {
        delay(1000)
        if (token.isNullOrEmpty()) {
            navController.navigate(AppRoute.Auth.route) {
                popUpTo(AppRoute.Splash.route) { inclusive = true }
            }
        } else {
            navController.navigate(AppRoute.Main.route) {
                popUpTo(AppRoute.Splash.route) { inclusive = true }
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}