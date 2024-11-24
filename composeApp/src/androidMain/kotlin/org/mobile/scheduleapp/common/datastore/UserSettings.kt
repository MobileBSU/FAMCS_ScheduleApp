package org.mobile.scheduleapp.common.datastore

import kotlinx.serialization.Serializable
import org.mobile.scheduleapp.auth.domain.model.AuthResultData

@Serializable
data class UserSettings(
    val id: Int = -1,
    val name: String = "",
    val avatar: String? = null,
    val token: String = ""
)

fun UserSettings.toAuthResultData(): AuthResultData{
    return AuthResultData(id, name, avatar, token)
}

fun AuthResultData.toUserSettings(): UserSettings{
    return UserSettings(id, name, avatar, token)
}