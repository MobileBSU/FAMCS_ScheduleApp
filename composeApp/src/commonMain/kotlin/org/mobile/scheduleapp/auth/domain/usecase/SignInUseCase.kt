package org.mobile.scheduleapp.auth.domain.usecase

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.mobile.scheduleapp.auth.domain.model.AuthResultData
import org.mobile.scheduleapp.auth.domain.repository.AuthRepository
import org.mobile.scheduleapp.common.util.Result

class SignInUseCase: KoinComponent {
    private val repository: AuthRepository by inject()

    suspend operator fun invoke(
        email: String,
        password: String
    ): Result<AuthResultData> {
        if (email.isBlank() || "@" !in email) {
            return Result.Error(
                message = "Invalid email"
            )
        }
        if (password.isBlank() || password.length < 4) {
            return Result.Error(
                message = "Invalid password or too short"
            )
        }

        return repository.signIn(email, password)
    }


}