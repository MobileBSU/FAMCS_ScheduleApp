package org.mobile.scheduleapp.presentation.view.navigation.graphs

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import org.mobile.scheduleapp.presentation.screens.myscheduleScreen.MyScheduleScreen
import org.mobile.scheduleapp.presentation.screens.profileScreen.ProfileScreen
import org.mobile.scheduleapp.presentation.screens.profileScreen.editProfleScreen.EditProfileScreen
import org.mobile.scheduleapp.presentation.screens.searchScreens.lecturersSearchScreen.LecturersSearchScreen
import org.mobile.scheduleapp.presentation.screens.searchScreens.groupSearchScreen.GroupSearchScreen
import org.mobile.scheduleapp.presentation.view.navigation.AppRoute
import org.mobile.scheduleapp.presentation.view.navigation.bottomBar.BottomNavigationBar
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme


@RequiresApi(Build.VERSION_CODES.O)
fun NavGraphBuilder.mainNavGraph(navController: NavController) {
    navigation(startDestination = AppRoute.MySchedule.route, route = AppRoute.Main.route) {
        composable(AppRoute.MySchedule.route) {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { innerPadding ->
                ScheduleAppTheme {
                    MyScheduleScreen(
                        navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }

        composable(
            route = AppRoute.DetailedTeacher.route,
            arguments = listOf(navArgument("teacher") {
                type = NavType.StringType
                nullable = false
            })
        ) { navBackStackEntry ->
            val teacherId = navBackStackEntry.arguments?.getString("teacher")
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { innerPadding ->
                MyScheduleScreen(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding),
                    teacherId = teacherId?.toLong()
                )
            }
        }

        composable(
            route = AppRoute.EditScreen.route
        ) {
            navBackStackEntry ->

            Scaffold (
                bottomBar = { BottomNavigationBar(navController)}
            ){  innerPadding ->
                EditProfileScreen(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding),
                )
            }
        }


        composable(AppRoute.Schedule.route) {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { innerPadding ->
                GroupSearchScreen(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding))
            }

        }
        composable(AppRoute.Lecture.route) {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { innerPadding ->
                LecturersSearchScreen(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }

        }

        composable(AppRoute.Profile.route) {
            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { innerPadding ->
                ProfileScreen(
                    modifier = Modifier.padding(innerPadding),
                    navController = navController
                )
            }
        }

        composable(AppRoute.DetailedGroup.route) { navBackStackEntry ->
            val groupId = navBackStackEntry.arguments?.getString("group")

            Scaffold(
                bottomBar = { BottomNavigationBar(navController) }
            ) { innerPadding ->
                MyScheduleScreen(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding),
                    groupId = groupId?.toLong()
                )
            }
        }

    }
}
