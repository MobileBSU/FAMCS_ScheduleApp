package org.mobile.scheduleapp.searchGroup.data

import kotlinx.serialization.Serializable

@Serializable
internal data class GroupSearchResponse(
    val data: List<GroupSearchResponseData>? = null,
    val errorMessage: String? = null
)


@Serializable
internal data class GroupSearchResponseData (
    val id: Long,
    val course: Int,
    val groupNumber: Int,
    val name: String,
    val subGroupNumber: Int?,
)