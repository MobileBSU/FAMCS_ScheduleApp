package org.mobile.scheduleapp.presentation.view.navigation.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import org.mobile.scheduleapp.presentation.view.navigation.AppRoute


fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    navigation(startDestination = AppRoute.MySchedule.route, route = AppRoute.Main.route) {
        composable(AppRoute.MySchedule.route) {
//            Scaffold(
//                bottomBar = { BottomNavigationBar(navController) }
//            ) { innerPadding ->
//                MyScheduleScreen(modifier = Modifier.padding(innerPadding))
//            }
//        }
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
}