package org.mobile.scheduleapp.presentation.screens.authScreens.login

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mobile.scheduleapp.auth.domain.usecase.SignInUseCase
import org.mobile.scheduleapp.common.datastore.UserSettings
import org.mobile.scheduleapp.common.datastore.toUserSettings
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.presentation.utils.StatefulViewModel

class LoginViewModel(
    private val signInUseCase: SignInUseCase,
    private val dataStore: DataStore<UserSettings>
) :
    StatefulViewModel<LoginUiState>(LoginUiState()), LoginController {
    val uiState : StateFlow<LoginUiState>
        get() = stateFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = state
        )

    fun signIn() {
        viewModelScope.launch {
            updateState { copy(isAuthenticating = true) }

            val authResultData = signInUseCase(
                uiState.value.email,
                uiState.value.password
            )

            updateState {
                when(authResultData) {
                    is Result.Error -> {
                        copy(
                            isAuthenticating = false,
                            authErrorMessage = authResultData.message)
                    }
                    is Result.Success -> {
                        viewModelScope.launch {
                            dataStore.updateData {
                                authResultData.data!!.toUserSettings()
                            }
                        }
                        copy(
                            isAuthenticating = false,
                            authenticationSucceed = true
                        )
                    }
                } }
        }
    }

    override fun updateEmail(input: String) {
        updateState { copy(email = input) }
    }

    override fun updatePassword(input: String) {
        updateState { copy(password = input) }
    }
}


data class LoginUiState (
    val email: String = "",
    val password: String = "",
    var isAuthenticating: Boolean = false,
    var authErrorMessage: String? = null,
    var authenticationSucceed: Boolean = false
)