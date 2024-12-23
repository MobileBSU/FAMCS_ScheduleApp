package org.mobile.scheduleapp.common.datastore

import kotlinx.serialization.Serializable
import org.mobile.scheduleapp.auth.domain.model.AuthResultData
import org.mobile.scheduleapp.presentation.screens.profileScreen.ProfileUiItem

@Serializable
data class UserSettings(
    val id: Long = -1,
    val name: String = "",
    val imageUrl: String? = null,
    val token: String = "",
    val email: String = "",
    val course: Int = 0,
    val group: Int = 0
)

fun UserSettings.toAuthResultData(): AuthResultData{
    return AuthResultData(id, name, imageUrl, token, email)
}

fun AuthResultData.toUserSettings(): UserSettings{
    return UserSettings(id, name, imageUrl, token, email)
}

fun UserSettings.toProfileUiItem(): ProfileUiItem {
    return ProfileUiItem(
        id = id,
        name = name,
        urlStr = imageUrl,
        email = email,
        course = course,
        group = group
    )
}