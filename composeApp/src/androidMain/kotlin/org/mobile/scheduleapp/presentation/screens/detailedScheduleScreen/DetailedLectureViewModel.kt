package org.mobile.scheduleapp.presentation.screens.detailedScheduleScreen

import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.annotation.StringRes
import androidx.datastore.core.DataStore
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mobile.scheduleapp.common.datastore.UserSettings
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.common.util.toID
import org.mobile.scheduleapp.common.util.toScheduleUiState
import org.mobile.scheduleapp.common.util.toUiState
import org.mobile.scheduleapp.presentation.utils.StatefulViewModel
import org.mobile.scheduleapp.searchGroup.domain.usecase.GetGroupByIdUseCase
import org.mobile.scheduleapp.searchTeacher.domain.usecase.GetTeacherByIdUseCase
import org.mobile.scheduleapp.student.data.StudentService
import org.mobile.scheduleapp.student.domain.GetStudentUseCase
import org.mobile.scheduleapp.subject.domain.usecase.GetSubjectsByGroupUseCase
import org.mobile.scheduleapp.subject.domain.usecase.GetSubjectsByTeacherUseCase

class DetailedLectureViewModel(
    private val getStudentUseCase: GetStudentUseCase,
    private val dataStore: DataStore<UserSettings>,
    private val subjectsByTeacherUseCase: GetSubjectsByTeacherUseCase,
    private val subjectsByGroupUseCase: GetSubjectsByGroupUseCase,
    private val getTeacherByIdUseCase: GetTeacherByIdUseCase,
    private val groupByIdUseCase: GetGroupByIdUseCase
): StatefulViewModel<ScheduleUiState>(ScheduleUiState()), ScheduleController {

    init {
        getUser()
        getGroup()
    }
    val uiState: StateFlow<ScheduleUiState>
        get() = stateFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = state
        )

    override fun getUser() {
        viewModelScope.launch {
            dataStore.data.collect { profile ->
                updateState {
                    copy(userId = profile.id)
                }
            }
        }
    }

    private fun getGroup() {
        viewModelScope.launch {

            val result = getStudentUseCase(state.userId)

            when(result) {
                is Result.Error -> {

                    updateState {
                        copy(errorMessage = result.message)
                    }
                }
                is Result.Success -> {

                    updateState {
                        copy(groupId = result.data?.toID()!!)
                    }
                }
            }
        }
    }

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

    override fun getTeacherById(id: Long) {
        viewModelScope.launch {
            val result = getTeacherByIdUseCase(id)

            updateState {
                when (result) {
                    is Result.Success -> {
                        val teacher = result.data?.map { it.toUiState() }?.first()
                        copy(
                            groupName = teacher?.name
                        )
                    }

                    is Result.Error -> {
                        copy(
                            errorMessage = errorMessage + " " + result.message
                        )
                    }
                }
            } }
    }


    override fun getGroupById(id: Long) {
        viewModelScope.launch {
            val result = groupByIdUseCase(id)

            updateState {
                when (result){
                    is Result.Success -> {
                        val group = result.data?.map { it.toUiState()}?.first()
                        copy(
                            groupName = group?.name
                        )
                    }
                    is Result.Error -> {
                        copy(
                            errorMessage = errorMessage + " " + result.message
                        )
                    }
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
    fun getTeacherById(id: Long)
    fun getGroupSubject(id: Long)
    fun getGroupById(id: Long)
    fun getUser()
}

data class ScheduleUiState(
    val id: Long = 0,
    val list: List<SubjectsUi>? = null,
    val errorMessage: String? = null,
    val groupName: String? = null,
    val teacherName: String? = null,
    val userId: Long = 0,
    val groupId: Long? = 0
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