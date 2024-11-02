package org.mobile.scheduleapp.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.theming.Dimens
import org.mobile.scheduleapp.theming.NeutralDarkDarkest
import org.mobile.scheduleapp.theming.NeutralDarkLight

@Composable
fun BottomNavigationBar(modifier: Modifier = Modifier) {
    // Инициализация NavController для управления навигацией
    val navController = rememberNavController()
    var selectedItemIndex by rememberSaveable {
        mutableStateOf(0)
    }

    // Элементы нижней навигации
    val items = listOf(
        BottomNavigationItem(
            stringResource(id = R.string.my_schedule),
            painterResource(id = R.drawable.selected_heart),
            painterResource(id = R.drawable.unselected_heart),
            "my_schedule"  // Route для экрана
        ),
        BottomNavigationItem(
            stringResource(id = R.string.schedule),
            painterResource(id = R.drawable.selected_schedule),
            painterResource(id = R.drawable.unselected_schedule),
            "schedule"  // Route для экрана
        ),
        BottomNavigationItem(
            stringResource(id = R.string.lecture),
            painterResource(id = R.drawable.selected_lecture),
            painterResource(id = R.drawable.unselected_lecture),
            "lecture"  // Route для экрана
        ),
        BottomNavigationItem(
            stringResource(id = R.string.profile),
            painterResource(id = R.drawable.selected_profile),
            painterResource(id = R.drawable.unselected_profile),
            "profile"  // Route для экрана
        )
    )

    Scaffold(
        bottomBar = {
            NavigationBar(
                containerColor = Color.White
            ) {
                items.forEachIndexed { index, item ->
                    NavigationBarItem(
                        selected = (navController.currentDestination?.route == item.route)&&(selectedItemIndex == index),
                        onClick = {
                            selectedItemIndex = index
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.startDestinationId) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = {
                            Image(
                                painter = if(index==selectedItemIndex){
                                    item.selectedIcon
                                }else{
                                    item.unselectedIcon
                                },
                                contentDescription = item.title,
                                modifier = Modifier
                                    .size(Dimens.BottomNavIconSize)
                            )
                        },
                        label = {
                            if(index==selectedItemIndex) {
                                Text(
                                    text = item.title,
                                    color = NeutralDarkDarkest,
                                    style = TextStyle()
                                )
                            } else{
                                Text(
                                    text = item.title,
                                    color = NeutralDarkLight,
                                    style = TextStyle()
                                )
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Navigation(navController = navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun Navigation(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(navController = navController, startDestination = "my_schedule") {
        composable("my_schedule") { /*MyScheduleScreen()*/ }
        composable("schedule") { /*ScheduleScreen()*/}
        composable("lecture") { /*LectureScreen()*/}
        composable("profile") { /*ProfileScreen()*/ }
    }
}

@Preview
@Composable
fun Test3(){
    BottomNavigationBar()
}