package org.mobile.scheduleapp.presentation.screens.searchScreens

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
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.view.theming.NeutralLightMedium
import org.mobile.scheduleapp.presentation.view.theming.NeutralLightWhite
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme
import org.mobile.scheduleapp.screens.searchScreens.CustomSearchBar
import org.mobile.scheduleapp.screens.searchScreens.Divider
import org.mobile.scheduleapp.screens.searchScreens.Dot
import org.mobile.scheduleapp.screens.searchScreens.InfoText


//Test version of view model
class SearchViewModel : ViewModel() {
    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _isSearching = MutableStateFlow(false)
    val isSearching = _isSearching.asStateFlow()

    private val _groups = MutableStateFlow(allGroups)
    val groups = searchText.combine(_groups) { text, groups ->
        if (text.isBlank()) {
            groups
        } else {
            groups.filter { it.doesMatchSearchQuery(text) }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), _groups.value)

    fun onSearchTextChange(text: String) {
        _searchText.value = text
    }
}

data class Group(
    val course: Int,
    val groupNum: Int,
    val faculty: String,
    val major: String
) {
    fun doesMatchSearchQuery(query: String): Boolean {
        val matchingCombinations = listOf(
            "$course", "$groupNum", faculty, "$course$groupNum", "$course $groupNum", major
        )
        return matchingCombinations.any { it.contains(query, ignoreCase = true) }
    }
}

private val allGroups = listOf(
    Group(course = 4, groupNum = 13, faculty = "ФПМИ", major = "ПИ"),
    Group(course = 4, groupNum = 12, faculty = "ФПМИ", major = "ПИ")
)

@Composable
fun GroupSearchScreen(modifier: Modifier = Modifier) {
    val viewModel: SearchViewModel = viewModel()
    val searchText by viewModel.searchText.collectAsState()
    val groups by viewModel.groups.collectAsState()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        Header(stringResource(id = R.string.all_groups))
        CustomSearchBar(searchText = searchText, onTextChange = viewModel::onSearchTextChange)

        Spacer(modifier = Modifier.height(16.dp))

        if (searchText.isNotBlank()) {
            GroupList(
                groups = groups
            )
        }
    }
}



@Composable
fun GroupList(
    modifier: Modifier = Modifier,
    groups: List<Group>
) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(groups) { group ->
            org.mobile.scheduleapp.screens.searchScreens.GroupItem(
                modifier = modifier.background(NeutralLightWhite),
                group = group,
            )
        }
    }
}

@Composable
fun GroupItem(
    modifier: Modifier = Modifier,
    group: Group
) {
    Column(
        modifier = modifier
            .background(NeutralLightWhite)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text ="${group.groupNum} группа" ,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Black,
            modifier = modifier.padding(8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoText(text = group.faculty)
            Dot()
            InfoText(text = group.major)
            Dot()
            InfoText(text = stringResource(R.string.course, group.course))
        }
        Divider(color = NeutralLightMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun Test(){
    ScheduleAppTheme {

    }
}
