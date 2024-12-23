package org.mobile.scheduleapp.presentation.screens.authScreens.signup

import android.util.Log
import androidx.datastore.core.DataStore
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mobile.scheduleapp.auth.domain.usecase.SignUpUseCase
import org.mobile.scheduleapp.common.datastore.UserSettings
import org.mobile.scheduleapp.common.datastore.toUserSettings
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.presentation.utils.StatefulViewModel

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase,
    private val dataStore: DataStore<UserSettings>
): StatefulViewModel<SignUpState>(SignUpState()), SignUpController {

    val uiState: StateFlow<SignUpState>
        get() = stateFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = state
        )

    override fun signUp() {
        if (isPasswordSame()) {
            viewModelScope.launch {
                updateState { copy(isAuthenticating = true) }

                val authResultData = signUpUseCase(
                    uiState.value.email,
                    uiState.value.username,
                    uiState.value.password
                )

                updateState {
                    when (authResultData) {
                        is Result.Error -> {
                            copy(
                                isAuthenticating = false,
                                authErrorMessage = authResultData.message
                            )
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
                    }
                }
            }
        }
    }

    override fun updateUsername(input: String) {
        updateState { copy(username = input) }
    }

    private fun isPasswordSame(): Boolean {
        if (uiState.value.password == uiState.value.confirmedPassword) {
            return true
        }
        else {
            return false
        }
    }

    override fun updateEmail(input: String) {
        updateState { copy(email = input) }
    }

    override fun updatePassword(input: String) {
        updateState { copy(password = input) }
    }
    override fun updateConfirmedPassword(input: String) {
        updateState { copy(confirmedPassword = input) }
    }

    override fun updateIsTermsAccepted(input: Boolean) {
        updateState { copy(isTermsAccepted = input) }
    }
}

data class SignUpState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmedPassword: String = "",
    val isTermsAccepted: Boolean = false,
    var isAuthenticating: Boolean = false,
    var authErrorMessage: String? = null,
    var authenticationSucceed: Boolean = false
)
