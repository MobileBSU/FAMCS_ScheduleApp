package org.mobile.scheduleapp.presentation.view.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.mobile.scheduleapp.presentation.view.navigation.AppRoute
import org.mobile.scheduleapp.presentation.screens.authScreens.AuthViewModel
import org.mobile.scheduleapp.presentation.screens.authScreens.login.LoginScreen
import org.mobile.scheduleapp.presentation.screens.authScreens.signup.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavController, viewModel: AuthViewModel) {
    navigation(startDestination = AppRoute.Login.route, route = AppRoute.Auth.route) {
        composable(AppRoute.Login.route) {
            LoginScreen(
                navController = navController
            )
        }

        composable(AppRoute.SignUp.route) {
            SignUpScreen(
                navController = navController
            )
        }


    }
}