package org.mobile.scheduleapp.auth.domain.repository

import org.mobile.scheduleapp.auth.domain.model.AuthResultData
import org.mobile.scheduleapp.common.util.Result as Result

internal interface AuthRepository {

    suspend fun signUp(
        name: String,
        email: String,
        password: String
    ): Result<AuthResultData>

    suspend fun signIn(
        email: String,
        password: String
    ): Result<AuthResultData>
}