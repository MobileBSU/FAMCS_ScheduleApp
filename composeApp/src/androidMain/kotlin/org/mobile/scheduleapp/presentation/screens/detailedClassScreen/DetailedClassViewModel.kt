package org.mobile.scheduleapp.presentation.screens.detailedClassScreen

import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.common.util.toScheduleUiState
import org.mobile.scheduleapp.common.util.toSubject
import org.mobile.scheduleapp.common.util.toUiState
import org.mobile.scheduleapp.presentation.screens.detailedScheduleScreen.SubjectUiItem
import org.mobile.scheduleapp.presentation.screens.searchScreens.groupSearchScreen.Group
import org.mobile.scheduleapp.presentation.screens.searchScreens.lecturersSearchScreen.Lecturer
import org.mobile.scheduleapp.presentation.utils.StatefulViewModel
import org.mobile.scheduleapp.searchGroup.domain.usecase.GetGroupByIdUseCase
import org.mobile.scheduleapp.searchTeacher.domain.usecase.GetTeacherByIdUseCase
import org.mobile.scheduleapp.subject.domain.usecase.GetSubjectByIdUseCase

class DetailedClassViewModel(
    private val getSubjectByIdUseCase: GetSubjectByIdUseCase,
    private val getTeacherByIdUseCase: GetTeacherByIdUseCase,
    private val getGroupByIdUseCase: GetGroupByIdUseCase
): StatefulViewModel<DetailedClassUiState>(DetailedClassUiState()){

    init {
        getSubject(-1)
        getTeacher(-1)
        getGroup(-1)
    }

    val uiState: StateFlow<DetailedClassUiState>
        get() = stateFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = state
        )

    private fun getSubject(id: Long) {
        viewModelScope.launch {
            val result = getSubjectByIdUseCase(id)

            updateState {
                when(result) {
                    is Result.Success -> {
                        val detailedClassUiState = result.data?.toSubject()

                        copy(
                            id = detailedClassUiState?.id,
                            groupId = detailedClassUiState?.groupId,
                            teacherId = detailedClassUiState?.teacherId,
                            subjectUiItem = detailedClassUiState?.subjectUiItem
                        )
                    }

                    is Result.Error -> {
                        copy(errorMessage = result.message.orEmpty())
                    }
                }
            }
        }
    }

    private fun getTeacher(id: Long) {
        viewModelScope.launch {
            val result = getTeacherByIdUseCase(id)

            updateState {
                when(result) {
                    is Result.Success -> {
                        val teacher = result.data?.map { it.toUiState()}?.first()
                        copy(
                            teacherUiItem = teacher
                        )
                    }

                    is Result.Error -> {
                        copy(
                            errorMessage = errorMessage + "\n" + result.message
                        )
                    }
                }
            }
        }
    }

    private fun getGroup(id: Long) {
        viewModelScope.launch {
            val result = getGroupByIdUseCase(id)

            updateState {
                when(result) {
                    is Result.Success -> {
                        val group = result.data?.map { it.toUiState()}?.first()
                        copy(
                            groupUiItem = group
                        )
                    }

                    is Result.Error -> {
                        copy(
                            errorMessage = errorMessage + "\n" + result.message
                        )
                    }
                }
            }
        }
    }
}

data class DetailedClassUiState(
    val id: Long? = null,
    val groupId: Long? = null,
    var teacherId: Long? = null,
    val subjectUiItem: DetailedSubjectUiItem? = null,
    val teacherUiItem: Lecturer? = null,
    val groupUiItem: Group? = null,
    val errorMessage: String? = null,
)


data class DetailedSubjectUiItem(
    val name: String,
    @StringRes val day: Int,
    val startTime: String,
    val endTime: String,
    val classRoom: Int,
    @StringRes val lectureType: Int
)