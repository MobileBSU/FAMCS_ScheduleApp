package org.mobile.scheduleapp.auth.data

import org.mobile.scheduleapp.auth.domain.model.AuthResultData

internal fun AuthResponseData.toAuthResultData(): AuthResultData{
    return AuthResultData(id, name, avatar, token)
}