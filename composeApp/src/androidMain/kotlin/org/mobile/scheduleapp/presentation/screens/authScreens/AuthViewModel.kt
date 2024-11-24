package org.mobile.scheduleapp.presentation.screens.authScreens

import androidx.datastore.core.DataStore
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import org.mobile.scheduleapp.common.datastore.UserSettings
import org.mobile.scheduleapp.common.datastore.toAuthResultData
import org.mobile.scheduleapp.presentation.utils.StatefulViewModel

class AuthViewModel(
) : StatefulViewModel<AuthState>(AuthState()), AuthController {

    val isUserLoggedIn : StateFlow<Boolean>
        get() = stateFlow.map { it.isAuthed }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = state.isAuthed
        )

    override fun setUserLoggedIn(loggedIn: Boolean) {
        updateState { copy(isAuthed = loggedIn) }
    }
}

data class AuthState(
    val isAuthed: Boolean = false
)