package org.mobile.scheduleapp.presentation.view.navigation

sealed class AppRoute(val route: String) {
    object Auth : AppRoute("auth")
    object Main : AppRoute("main")
    object Splash: AppRoute("splash")

    object Login : AppRoute("auth/login")
    object SignUp : AppRoute("auth/signup")

    object MySchedule : AppRoute("main/my_schedule")
    object Schedule : AppRoute("main/schedule")
    object Lecture : AppRoute("main/lecture")
    object Profile : AppRoute("main/profile")

    object DetailedGroup : AppRoute("main/schedule/{group}")

}