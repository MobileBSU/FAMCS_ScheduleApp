package org.mobile.scheduleapp.presentation.screens.authScreens.signup

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.mobile.scheduleapp.auth.domain.usecase.SignUpUseCase
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.presentation.utils.StatefulViewModel

class SignUpViewModel(
    private val signUpUseCase: SignUpUseCase
): StatefulViewModel<SignUpState>(SignUpState()), SignUpController {

    val uiState: StateFlow<SignUpState>
        get() = stateFlow.stateIn(
            scope = viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = state
        )

    override fun signUp() {
        viewModelScope.launch {
            updateState { copy(isAuthenticating = true) }

            val authResultData = signUpUseCase(
                uiState.value.email,
                uiState.value.username,
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
                        copy(
                            isAuthenticating = false,
                            authenticationSucceed = true
                        )
                    }
                } }
        }
    }

    override fun updateUsername(input: String) {
        updateState { copy(username = input) }
    }

    override fun updateEmail(input: String) {
        updateState { copy(email = input) }
    }

    override fun updatePassword(input: String) {
        updateState { copy(password = input) }
    }

    override fun updateIsTermsAccepted(input: Boolean) {
        updateState { copy(isTermsAccepted = input) }
    }
}

data class SignUpState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val isTermsAccepted: Boolean = false,
    var isAuthenticating: Boolean = false,
    var authErrorMessage: String? = null,
    var authenticationSucceed: Boolean = false
)
