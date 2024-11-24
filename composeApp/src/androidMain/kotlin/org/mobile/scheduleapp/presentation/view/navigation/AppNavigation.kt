package org.mobile.scheduleapp.presentation.view.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import org.mobile.scheduleapp.presentation.view.navigation.graphs.authNavGraph
import org.mobile.scheduleapp.presentation.view.navigation.graphs.mainNavGraph
import org.mobile.scheduleapp.presentation.screens.authScreens.AuthViewModel
import org.mobile.scheduleapp.presentation.view.navigation.graphs.splashNavGraph

@Composable
fun AppNavigation(
    token: String?
) {
    val navController = rememberNavController()


    NavHost(
        navController = navController,
        startDestination = AppRoute.Splash.route
    ) {
        splashNavGraph(navController, token)
        authNavGraph(navController)
        mainNavGraph(navController)
    }

}
