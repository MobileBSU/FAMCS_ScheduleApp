package org.mobile.scheduleapp.presentation.screens.searchScreens.groupSearchScreen

import android.util.Log
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.common.util.toUiState
import org.mobile.scheduleapp.presentation.utils.StatefulViewModel
import org.mobile.scheduleapp.searchGroup.domain.usecase.GetAllGroupsUseCase
import org.mobile.scheduleapp.searchGroup.domain.usecase.GetGroupsByNameUseCase

class GroupSearchViewModel(
    private val groupsUseCase: GetAllGroupsUseCase,
    private val groupsByNameUseCase: GetGroupsByNameUseCase
): StatefulViewModel<GroupUiState>(GroupUiState()), GroupSearchController {

    init {
        getAllGroups()
    }

    val uiState: StateFlow<GroupUiState>
        get() = stateFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = state
        )


    private fun getAllGroups() {
        viewModelScope.launch {
            val response = groupsUseCase()
            updateState {
                when (response) {
                    is Result.Success -> {
                        val groups = response.data
                        copy(groups = groups?.map{ it.toUiState()})

                    }
                    is Result.Error -> {
                        copy(
                            errorMessage =  response.message ?: "An unknown error occurred"
                        )
                    }
                }
            }

            Log.d("GroupState", " size ${state.groups?.size}")


        }
    }

    override fun getGroupByName(input: String){
        viewModelScope.launch {
            val response = groupsByNameUseCase(input)

            updateState {
                when (response) {
                    is Result.Success -> {
                        val groups = response.data
                        copy(groups = groups?.map{it.toUiState()})
                    }
                    is Result.Error -> {
                        copy(
                            errorMessage =  response.message ?: "An unknown error occurred"
                        )
                    }
                }
            }
        }
    }

    override fun updateSearchText(input: String) {
        updateState { copy(searchText = input) }
    }
}

data class GroupUiState(
    val groups : List<Group>? = null,
    val errorMessage : String? = null,
    val searchText: String = ""

)

data class Group(
    val id: Long = 0,
    val course: Int = 0,
    val groupNumber: Int = 0,
    val name: String = "",
    val subGroupNumber: Int? = null,
)

interface GroupSearchController {
    fun getGroupByName(input: String)
    fun updateSearchText(input: String)
}