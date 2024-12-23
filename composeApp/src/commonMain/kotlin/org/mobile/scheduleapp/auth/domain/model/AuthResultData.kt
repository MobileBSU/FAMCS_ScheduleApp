package org.mobile.scheduleapp.auth.domain.model

data class AuthResultData(
    val id: Long,
    val name: String,
    val imageUrl: String? = null,
    val token: String,
    val email: String
)