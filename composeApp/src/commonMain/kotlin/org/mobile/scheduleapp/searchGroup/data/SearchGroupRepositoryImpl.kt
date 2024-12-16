package org.mobile.scheduleapp.searchGroup.data

import kotlinx.coroutines.withContext
import org.mobile.scheduleapp.common.util.DispatcherProvider
import org.mobile.scheduleapp.searchGroup.domain.model.GroupSearchResultData
import org.mobile.scheduleapp.searchGroup.domain.repository.SearchGroupRepository
import org.mobile.scheduleapp.common.util.Result as Result

internal class SearchGroupRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val searchGroupService: SearchGroupService
) : SearchGroupRepository {
    override suspend fun getAllGroups(): Result<List<GroupSearchResultData>> {
        return withContext(dispatcher.io) {
            try {
                val groupSearchResponse = searchGroupService.getAllGroups()

                if (groupSearchResponse.data == null) {
                    Result.Error(
                        message = groupSearchResponse.errorMessage!!
                    )
                } else {
                    Result.Success(
                        data = groupSearchResponse.data.map {it.toGroupSearchResultData()}
                    )
                }
            } catch (e: Exception) {
                Result.Error(
                    message = "Ooops, something is wrong + $e"
                )
            }
        }
    }

    override suspend fun getGroupByName(input: String): Result<List<GroupSearchResultData>> {
        return withContext(dispatcher.io) {
            try {
                val request = GroupSearchRequest(input)

                val groupSearchResponse = searchGroupService.getGroupsByName(request)

                if (groupSearchResponse.data == null) {
                    Result.Error(
                        message = groupSearchResponse.errorMessage
                    )
                } else {
                    Result.Success(
                        data = groupSearchResponse.data.map { it.toGroupSearchResultData() }
                    )
                }
            } catch (e: Exception) {
                Result.Error(
                    message = "Oops, we couldn`t send your request"
                )
            }
        }
    }

    override suspend fun getGroupById(id: Long): Result<List<GroupSearchResultData>> {
        return withContext(dispatcher.io) {
            try {
                val groupSearchResponse = searchGroupService.getGroupById(id)

                if (groupSearchResponse.data == null) {
                    Result.Error(
                        message = groupSearchResponse.errorMessage
                    )
                } else {
                    Result.Success(
                        data = groupSearchResponse.data.map { it.toGroupSearchResultData() }
                    )
                }
            } catch (e: Exception) {
                Result.Error(
                    message = "Oops, we couldn`t send your request"
                )
            }
        }
    }
}