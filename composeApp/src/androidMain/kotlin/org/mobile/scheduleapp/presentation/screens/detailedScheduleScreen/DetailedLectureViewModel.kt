package org.mobile.scheduleapp.presentation.screens.detailedScheduleScreen

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.common.util.toScheduleUiState
import org.mobile.scheduleapp.presentation.utils.StatefulViewModel
import org.mobile.scheduleapp.subject.domain.usecase.GetSubjectsByGroupUseCase
import org.mobile.scheduleapp.subject.domain.usecase.GetSubjectsByTeacherUseCase

class DetailedLectureViewModel(
    private val subjectsByTeacherUseCase: GetSubjectsByTeacherUseCase,
    private val subjectsByGroupUseCase: GetSubjectsByGroupUseCase
): StatefulViewModel<ScheduleUiState>(ScheduleUiState()), ScheduleController {

    val uiState: StateFlow<ScheduleUiState>
        get() = stateFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = state
        )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getTeacherSubject(id: Long) {
        viewModelScope.launch {
            val result = subjectsByTeacherUseCase(id)

            updateState {
                when (result) {
                    is Result.Success -> {
                        val scheduleUiStates = result.data?.toScheduleUiState(id)

                        val allSubjects = scheduleUiStates?.flatMap { it.list ?: emptyList() }

                        copy(list = allSubjects)
                    }
                    is Result.Error -> {
                        copy(errorMessage = result.message.orEmpty())
                    }
                    else -> this
                }
            }
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun getGroupSubject(id: Long) {
        viewModelScope.launch {
            val result = subjectsByGroupUseCase(id)

            updateState {
                when (result) {
                    is Result.Success -> {
                        val scheduleUiStates = result.data?.toScheduleUiState(id)

                        val allSubjects = scheduleUiStates?.flatMap { it.list ?: emptyList() }

                        copy(list = allSubjects)
                    }
                    is Result.Error -> {
                        copy(errorMessage = result.message.orEmpty())
                    }
                    else -> this
                }
            }
        }
    }

}

interface ScheduleController {
    fun getTeacherSubject(id: Long)
    fun getGroupSubject(id: Long)
}
data class ScheduleUiState(
    val id: Long = 0,
    val list: List<SubjectsUi>? = null,
    val errorMessage: String? = null
)

data class SubjectsUi(
    val day: String = "",
    val date: String = "",
    val subjects: List<SubjectUiItem>? = null
)

data class SubjectUiItem(
    val id: Long,
    val name: String,
    val day: String = "",
    val date: String = "",
    val startTime: String,
    val endTime: String,
    val classRoom: Int,
    val groupId: Long?,
    val teacherId: Long,
    @StringRes val lectureType: Int
)