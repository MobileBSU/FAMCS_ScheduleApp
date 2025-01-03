package org.mobile.scheduleapp.presentation.screens.myscheduleScreen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import org.koin.androidx.compose.koinViewModel
import org.mobile.scheduleapp.R
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
    groupId: Long? = null,
    teacherId: Long? = null
) {
    val viewModel: DetailedLectureViewModel = koinViewModel()

    viewModel.getUser()
    val userId = viewModel.stateFlow.collectAsState().value.groupId

    var isStub by remember { mutableStateOf(true) }
    var isBackIconHidden by remember { mutableStateOf(false) }

    LaunchedEffect(groupId, teacherId, userId) {
        when {
            groupId != null -> {
                viewModel.getGroupSubject(groupId)
                viewModel.getGroupById(groupId)
                isStub = false
                isBackIconHidden = false
            }
            userId != null -> {
                viewModel.getGroupSubject(userId)
                viewModel.getGroupById(userId)
                isStub = false
                isBackIconHidden = true
            }
            teacherId != null -> {
                Log.d("TEST123", "HERE IS TEACHER $teacherId")
                viewModel.getTeacherSubject(teacherId)
                viewModel.getTeacherById(teacherId)
                isStub = false
                isBackIconHidden = false
            }
        }
    }

    val state = viewModel.uiState.collectAsState().value

    MyScheduleScreenLayout(
        onBackIconClicked = {
            navController.popBackStack()
        },
        onCardClicked = {},
        state = state,
        isStubActive = isStub,
        isBackIconHidden = isBackIconHidden
    )
}
@Composable
fun MyScheduleScreenLayout(
    modifier: Modifier = Modifier,
    state: ScheduleUiState,
    onBackIconClicked: () -> Unit,
    onCardClicked: () -> Unit,
    isStubActive: Boolean = false,
    isBackIconHidden: Boolean = false
) {
    if (!isStubActive) {
        LazyColumn(
            modifier = modifier
                .fillMaxSize()
                .padding(horizontal = Dimens.LargeSpaceBetween)
        ) {
            item {
                state.groupName?.let {
                    TopBar(
                        title = it,
                        onBackIconClicked = onBackIconClicked,
                        isBackIconHidden = isBackIconHidden
                    )
                }

                state.teacherName?.let {
                    TopBar(
                        title = it,
                        onBackIconClicked = onBackIconClicked,
                        isBackIconHidden = isBackIconHidden
                    )
                }
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
    } else {

        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = modifier
                    .align(Alignment.Center),
                text = stringResource(R.string.schedule_stub),
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}


