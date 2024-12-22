package org.mobile.scheduleapp.presentation.screens.profileScreen

import androidx.datastore.core.DataStore
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.mobile.scheduleapp.common.datastore.UserSettings
import org.mobile.scheduleapp.common.datastore.toProfileUiItem
import org.mobile.scheduleapp.presentation.utils.StatefulViewModel

class ProfileViewModel(
    private val dataStore: DataStore<UserSettings>
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

}

interface ProfileController{
    fun onSetNewPhotoClicked(input: String)
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
