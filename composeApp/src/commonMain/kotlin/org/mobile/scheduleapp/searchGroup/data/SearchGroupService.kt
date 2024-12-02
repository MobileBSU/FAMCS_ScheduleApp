package org.mobile.scheduleapp.searchGroup.data

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.setBody
import org.mobile.scheduleapp.common.data.KtorApi

internal class SearchGroupService: KtorApi() {

    suspend fun getAllGroups(): GroupSearchResponse = client.get{
        endPoint(path = "groups")
    }.body()

    suspend fun getGroupsByName(request: GroupSearchRequest): GroupSearchResponse = client.get {
        endPoint(path = "groupByName")
        setBody(request)
    }.body()
}