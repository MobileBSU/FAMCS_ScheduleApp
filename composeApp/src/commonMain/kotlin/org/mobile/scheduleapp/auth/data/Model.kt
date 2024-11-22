package org.mobile.scheduleapp.auth.data

import kotlinx.serialization.Serializable

@Serializable
internal data class SignUpRequest(
    val name: String,
    val email: String,
    val password: String
)

@Serializable
internal data class SignInRequest (
    val email: String,
    val password: String
)

@Serializable
internal data class AuthResponse(
    val data: AuthResponseData?= null,
    val errorMessage: String? = null
)

@Serializable
internal data class AuthResponseData (
    val id: Int,
    val name: String,
    val avatar: String? = null,
    val token: String
)