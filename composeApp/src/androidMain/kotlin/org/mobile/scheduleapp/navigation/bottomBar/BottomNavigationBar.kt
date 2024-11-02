package org.mobile.scheduleapp.navigation.bottomBar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.theming.Dimens
import org.mobile.scheduleapp.theming.NeutralDarkDarkest
import org.mobile.scheduleapp.theming.NeutralDarkLight

@Composable
fun BottomNavigationBar(navController: NavController) {
    var selectedItemIndex by rememberSaveable { mutableStateOf(0) }

    val items = listOf(
        BottomNavigationItem(
            stringResource(id = R.string.my_schedule),
            painterResource(id = R.drawable.selected_heart),
            painterResource(id = R.drawable.unselected_heart),
            "my_schedule"
        ),
        BottomNavigationItem(
            stringResource(id = R.string.schedule),
            painterResource(id = R.drawable.selected_schedule),
            painterResource(id = R.drawable.unselected_schedule),
            "schedule"
        ),
        BottomNavigationItem(
            stringResource(id = R.string.lecture),
            painterResource(id = R.drawable.selected_lecture),
            painterResource(id = R.drawable.unselected_lecture),
            "lecture"
        ),
        BottomNavigationItem(
            stringResource(id = R.string.profile),
            painterResource(id = R.drawable.selected_profile),
            painterResource(id = R.drawable.unselected_profile),
            "profile"
        )
    )

    selectedItemIndex = items.indexOfFirst { it.route == navController.currentDestination?.route }

    NavigationBar(
        containerColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemIndex == index,
                onClick = {
                    if (selectedItemIndex != index) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                },
                icon = {
                    Image(
                        painter = if (selectedItemIndex == index) {
                            item.selectedIcon
                        } else {
                            item.unselectedIcon
                        },
                        contentDescription = item.title,
                        modifier = Modifier.size(Dimens.BottomNavIconSize)
                    )
                },
                label = {
                    Text(
                        text = item.title,
                        color = if (selectedItemIndex == index) NeutralDarkDarkest else NeutralDarkLight,
                        style = TextStyle()
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun Test3(){
    BottomNavigationBar(rememberNavController())
}