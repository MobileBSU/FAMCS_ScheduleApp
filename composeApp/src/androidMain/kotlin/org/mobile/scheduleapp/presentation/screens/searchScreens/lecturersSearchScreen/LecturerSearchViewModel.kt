package org.mobile.scheduleapp.presentation.screens.searchScreens.lecturersSearchScreen

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.common.util.toUiState
import org.mobile.scheduleapp.presentation.utils.StatefulViewModel
import org.mobile.scheduleapp.searchTeacher.domain.usecase.GetAllTeachersUseCase
import org.mobile.scheduleapp.searchTeacher.domain.usecase.GetTeachersByNameUseCase

class LecturerSearchViewModel(
    private val getAllTeachersUseCase: GetAllTeachersUseCase,
    private val getTeachersByNameUseCase: GetTeachersByNameUseCase
) : StatefulViewModel<LecturerUiState> (LecturerUiState()), LecturerSearchController {

    init {
        getAlTeachers()
    }

    val uiState: StateFlow<LecturerUiState>
        get() = stateFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = state
        )


    private fun getAlTeachers() {
        viewModelScope.launch {
            val result = getAllTeachersUseCase()

            updateState {
                when(result) {
                    is Result.Success -> {
                        val teachers = result.data
                        copy(lecturers = teachers?.map { it.toUiState() })
                    }

                    is Result.Error -> {
                        copy(
                            errorMessage = result.message ?: "An unknown error occurred"
                        )
                    }
                }
            }
        }
    }

    override fun getTeacherByName(name: String) {
        viewModelScope.launch {
            val result = getTeachersByNameUseCase(name)

            updateState {
                when(result) {
                    is Result.Success -> {
                        val teachers = result.data
                        copy(lecturers = teachers?.map { it.toUiState() })
                    }

                    is Result.Error -> {
                        copy(
                            errorMessage =  result.message ?: "An unknown error occurred"
                        )
                    }
                }
            }
        }
    }

    override fun updateSearchText(name: String) {
        updateState { copy(searchTest = name) }
    }

}

interface LecturerSearchController {
    fun updateSearchText(name: String)
    fun getTeacherByName(name: String)

}

data class LecturerUiState(
    val lecturers: List<Lecturer>? = null,
    val errorMessage: String? = null,
    val searchTest : String = ""
)

data class Lecturer(
    val id: Long = 0,
    val name: String = "",
    val bio: String = "",
    val imageUrl: String? = null
)