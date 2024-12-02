package org.mobile.scheduleapp.presentation.view.navigation.graphs

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.mobile.scheduleapp.presentation.screens.MyScheduleScreen
import org.mobile.scheduleapp.presentation.screens.searchScreens.lecturersSearchScreen.LecturersSearchScreen
import org.mobile.scheduleapp.presentation.screens.searchScreens.groupSearchScreen.GroupSearchScreen
import org.mobile.scheduleapp.presentation.view.navigation.AppRoute
import org.mobile.scheduleapp.presentation.view.navigation.bottomBar.BottomNavigationBar
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme


fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    navigation(startDestination = AppRoute.MySchedule.route, route = AppRoute.Main.route) {
        composable(AppRoute.MySchedule.route) {
            ScheduleAppTheme {
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    ScheduleAppTheme { MyScheduleScreen(navController = navController ,modifier = Modifier.padding(innerPadding)) }
                }
            }
        }
        composable(AppRoute.Schedule.route) {
            ScheduleAppTheme {
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    GroupSearchScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
        composable(AppRoute.Lecture.route) {
            ScheduleAppTheme {
                Scaffold(
                    bottomBar = { BottomNavigationBar(navController) }
                ) { innerPadding ->
                    LecturersSearchScreen(modifier = Modifier.padding(innerPadding))
                }
            }
            }

            composable(AppRoute.Profile.route) {
//            Scaffold(
//                bottomBar = { BottomNavigationBar(navController) }
//            ) { innerPadding ->
//                ProfileScreen(modifier = Modifier.padding(innerPadding))
//            }
            }
        }
    }
