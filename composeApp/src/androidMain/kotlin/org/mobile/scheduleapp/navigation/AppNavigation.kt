package org.mobile.scheduleapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.navigation.compose.NavHost

import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import org.mobile.scheduleapp.navigation.graphs.authNavGraph
import org.mobile.scheduleapp.navigation.graphs.mainNavGraph
import org.mobile.scheduleapp.screens.authScreens.AuthViewModel

@Composable
fun AppNavigation() {
    val viewModel: AuthViewModel = koinViewModel()
    val navController = rememberNavController()
    val isUserLoggedIn by viewModel.isUserLoggedIn.collectAsState()


    NavHost(
        navController = navController,
        startDestination = if (isUserLoggedIn) AppRoute.Main.route else AppRoute.Auth.route
    ) {
        authNavGraph(navController, viewModel)
        mainNavGraph(navController)
    }

}