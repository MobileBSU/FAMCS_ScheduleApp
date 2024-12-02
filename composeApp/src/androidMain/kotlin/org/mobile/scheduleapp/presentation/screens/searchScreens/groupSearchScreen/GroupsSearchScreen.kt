package org.mobile.scheduleapp.presentation.screens.searchScreens.groupSearchScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import org.koin.androidx.compose.koinViewModel
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.screens.searchScreens.Header
import org.mobile.scheduleapp.presentation.view.components.GroupList
import org.mobile.scheduleapp.presentation.view.theming.NeutralLightMedium
import org.mobile.scheduleapp.presentation.view.theming.NeutralLightWhite
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme
import org.mobile.scheduleapp.screens.searchScreens.CustomSearchBar
import org.mobile.scheduleapp.screens.searchScreens.Divider
import org.mobile.scheduleapp.screens.searchScreens.Dot
import org.mobile.scheduleapp.screens.searchScreens.InfoText

@Composable
fun  GroupSearchScreen(modifier: Modifier = Modifier) {
    val viewModel: GroupSearchViewModel = koinViewModel()
    val state = viewModel.uiState.collectAsState().value

    GroupSearchLayout(modifier = modifier, state = state, controller = viewModel)

}
@Composable
fun GroupSearchLayout(
    modifier: Modifier = Modifier,
    state: GroupUiState,
    controller: GroupSearchController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        Header(stringResource(id = R.string.all_groups))
        CustomSearchBar(
            searchText = state.searchText,
            onTextChange = {
                controller.updateSearchText(it)
                controller.getGroupByName(it)
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        state.groups?.let {
            GroupList(
                groups = it
            )
        }
    }
}

