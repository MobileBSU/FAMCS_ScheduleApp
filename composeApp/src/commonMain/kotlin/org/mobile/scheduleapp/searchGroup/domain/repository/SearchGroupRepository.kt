package org.mobile.scheduleapp.searchGroup.domain.repository

import org.mobile.scheduleapp.searchGroup.domain.model.GroupSearchResultData
import org.mobile.scheduleapp.common.util.Result as Result
internal interface SearchGroupRepository {

    suspend fun getAllGroups(): Result<List<GroupSearchResultData>>

    suspend fun getGroupByName(
        input: String
    ): Result<List<GroupSearchResultData>>

    suspend fun getGroupById(id: Long): Result<List<GroupSearchResultData>>

    suspend fun getGroupByCourse(course: Int, group: Int): Result<List<GroupSearchResultData>>
}