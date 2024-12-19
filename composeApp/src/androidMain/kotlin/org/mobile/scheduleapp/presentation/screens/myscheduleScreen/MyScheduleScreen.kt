package org.mobile.scheduleapp.presentation.screens.myscheduleScreen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import org.mobile.scheduleapp.presentation.screens.detailedScheduleScreen.DetailedLectureViewModel
import org.mobile.scheduleapp.presentation.screens.detailedScheduleScreen.ScheduleController
import org.mobile.scheduleapp.presentation.screens.detailedScheduleScreen.ScheduleUiState
import org.mobile.scheduleapp.screens.detailedScheduleScreen.DaySchedule
import org.mobile.scheduleapp.screens.detailedScheduleScreen.TopBar
import org.mobile.scheduleapp.presentation.view.theming.Dimens

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun MyScheduleScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
    groupId: Long? = null
) {
    val viewModel: DetailedLectureViewModel = koinViewModel()

    groupId?.let {
        viewModel.getGroupSubject(it)
    }

    val state = viewModel.uiState.collectAsState().value

    MyScheduleScreenLayout(
        onBackIconClicked = {},
        onCardClicked = {},
        label = "",
        state = state
    )

}
@Composable
fun MyScheduleScreenLayout(
    modifier: Modifier = Modifier,
    state: ScheduleUiState,
    onBackIconClicked: () -> Unit,
    onCardClicked: () -> Unit,
    label: String,
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.LargeSpaceBetween)
    ) {
        item {
            TopBar(
                title = label,
                onBackIconClicked = onBackIconClicked
            )
        }

        item { Spacer(modifier = Modifier.height(Dimens.LargeSpaceBetween)) }

        state.list?.let { schedule ->
            items(schedule.size) { index ->
                DaySchedule(
                    schedule[index],
                    onCardClicked
                )
            }
        }

    }
}


