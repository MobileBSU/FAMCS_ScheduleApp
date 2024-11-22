package org.mobile.scheduleapp.auth.domain.model

data class AuthResultData(
    val id: Int,
    val name: String,
    val avatar: String? = null,
    val token: String
)