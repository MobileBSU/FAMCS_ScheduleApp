package org.mobile.scheduleapp.searchGroup.domain.model

data class GroupSearchResultData(
    val id: Long,
    val course: Int,
    val groupNumber: Int,
    val name: String,
    val subGroupNumber: Int?,
)