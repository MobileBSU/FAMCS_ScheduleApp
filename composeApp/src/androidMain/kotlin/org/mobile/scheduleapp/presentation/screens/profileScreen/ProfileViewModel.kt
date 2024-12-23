package org.mobile.scheduleapp.presentation.screens.profileScreen

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.mobile.scheduleapp.common.datastore.UserSettings
import org.mobile.scheduleapp.common.datastore.toProfileUiItem
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.common.util.toId
import org.mobile.scheduleapp.presentation.screens.authScreens.login.LoginScreen
import org.mobile.scheduleapp.presentation.utils.StatefulViewModel
import org.mobile.scheduleapp.searchGroup.domain.usecase.GetGroupByCourseUseCase
import org.mobile.scheduleapp.student.domain.UpdateStudentUseCase

class ProfileViewModel(
    private val dataStore: DataStore<UserSettings>,
    private val updateStudentUseCase: UpdateStudentUseCase,
    private val groupByCourseUseCase: GetGroupByCourseUseCase
): StatefulViewModel<ProfileUiState>(ProfileUiState()), ProfileController {

    init {
        viewModelScope.launch {
            dataStore.data
                .map { it.toProfileUiItem() }
                .catch { exception ->
                    updateState {
                        copy(message = (message ?: "") + " Error: ${exception.localizedMessage}")
                    }
                }
                .collect { profile ->
                    updateState {
                        copy(profileUiItem = profile)
                    }
                }
        }
    }

    override fun onSetNewPhotoClicked(input: String) {
        viewModelScope.launch {
            dataStore.updateData { currentSettings ->
                currentSettings.copy(imageUrl = input)
            }
            updateState {
                copy(profileUiItem = profileUiItem?.copy(urlStr = input))
            }
        }
    }

    override fun onButtonClicked(id: Long, name: String, email: String, course: Int, group: Int) {
        viewModelScope.launch {
            dataStore.updateData { currentSettings ->
                currentSettings.copy(name = name, email = email, course = course, group = group)
            }
            var groupId: Long? = 0
            val result = groupByCourseUseCase(course, group)

            when (result){
                is Result.Error -> {
                    updateState {
                        copy(message = result.message.toString())
                    }
                }
                is Result.Success -> {
                    groupId = result.data?.toId()
                }
            }

            val resultUpdate = updateStudentUseCase(id, name, email, null, null, groupId)

            when(resultUpdate) {
                is Result.Error -> {Log.d("TEST123", "Error" + resultUpdate.message.toString())}
                is Result.Success -> {Log.d("TEST123","Succes " + resultUpdate.message.toString())}
            }
        }
    }

    override fun updateName(name: String) {
        updateState {
            copy(profileUiItem = profileUiItem?.copy(name = name))
        }
    }

    override fun updateEmail(email: String) {
        updateState {
            copy(profileUiItem = profileUiItem?.copy(email = email))
        }
    }

    override fun updateCourse(course: String) {
        updateState {
            copy(profileUiItem = profileUiItem?.copy(course = course.toIntOrNull() ?: 0))
        }
    }

    override fun updateGroup(group: String) {
        updateState {
            copy(profileUiItem = profileUiItem?.copy(group = group.toIntOrNull() ?: 0))
        }
    }

}

interface ProfileController {
    fun onSetNewPhotoClicked(input: String)
    fun onButtonClicked(id: Long, name: String, email: String, course: Int, group: Int)
    fun updateName(name: String)
    fun updateEmail(email: String)
    fun updateCourse(course: String)
    fun updateGroup(group: String)
}


data class ProfileUiState(
    val message: String = "",
    val profileUiItem: ProfileUiItem? = null
)
data class ProfileUiItem(
    val name: String,
    val id: Long,
    val urlStr: String?,
    val email: String,
    val course: Int = 0,
    val group: Int = 0
)
