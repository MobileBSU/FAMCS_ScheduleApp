package org.mobile.scheduleapp.presentation.view.navigation.graphs

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.mobile.scheduleapp.presentation.screens.MyScheduleScreen
import org.mobile.scheduleapp.presentation.view.navigation.AppRoute
import org.mobile.scheduleapp.presentation.view.navigation.bottomBar.BottomNavigationBar


fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    navigation(startDestination = AppRoute.MySchedule.route, route = AppRoute.Main.route) {
        composable(AppRoute.MySchedule.route) {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { innerPadding ->
                MyScheduleScreen(navController = navController ,modifier = Modifier.padding(innerPadding))
            }
        }
            composable(AppRoute.Schedule.route) {
//            Scaffold(
//                bottomBar = { BottomNavigationBar(navController) }
//            ) { innerPadding ->
//                ScheduleScreen(modifier = Modifier.padding(innerPadding))
//            }
            }
            composable(AppRoute.Lecture.route) {
//            Scaffold(
//                bottomBar = { BottomNavigationBar(navController) }
//            ) { innerPadding ->
//                LectureScreen(modifier = Modifier.padding(innerPadding))
//            }
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
