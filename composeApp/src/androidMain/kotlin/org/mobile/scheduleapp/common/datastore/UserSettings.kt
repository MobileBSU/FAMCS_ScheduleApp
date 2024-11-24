package org.mobile.scheduleapp.common.datastore

import kotlinx.serialization.Serializable
import org.mobile.scheduleapp.auth.domain.model.AuthResultData

@Serializable
data class UserSettings(
    val id: Long = -1,
    val name: String = "",
    val imageUrl: String? = null,
    val token: String = ""
)

fun UserSettings.toAuthResultData(): AuthResultData{
    return AuthResultData(id, name, imageUrl, token)
}

fun AuthResultData.toUserSettings(): UserSettings{
    return UserSettings(id, name, imageUrl, token)
}