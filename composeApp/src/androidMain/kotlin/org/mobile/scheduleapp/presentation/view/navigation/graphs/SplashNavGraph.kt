package org.mobile.scheduleapp.presentation.view.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import org.mobile.scheduleapp.presentation.screens.SplashScreen
import org.mobile.scheduleapp.presentation.view.navigation.AppRoute

fun NavGraphBuilder.splashNavGraph(navController: NavController, token: String?) {
    composable(AppRoute.Splash.route) {
        SplashScreen(navController, token)
    }
}