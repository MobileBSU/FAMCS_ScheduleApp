package org.mobile.scheduleapp.presentation.screens.detailedScheduleScreen

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import org.mobile.scheduleapp.presentation.screens.myscheduleScreen.MyScheduleScreenLayout
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme

@Composable
fun DetailedScheduleScreen(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val viewModel: DetailedLectureViewModel = koinViewModel()
    val state = viewModel.uiState.collectAsState().value


    MyScheduleScreenLayout(
        onCardClicked = {},
        onBackIconClicked = {
            navController.popBackStack()
        },
        state = state,

    )
}
