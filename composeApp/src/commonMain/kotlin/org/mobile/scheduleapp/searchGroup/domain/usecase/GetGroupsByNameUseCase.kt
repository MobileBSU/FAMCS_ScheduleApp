package org.mobile.scheduleapp.searchGroup.domain.usecase

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.searchGroup.domain.model.GroupSearchResultData
import org.mobile.scheduleapp.searchGroup.domain.repository.SearchGroupRepository

class GetGroupsByNameUseCase : KoinComponent{
    private val repository: SearchGroupRepository by inject()

    suspend operator fun invoke(
        input: String
    ): Result<List<GroupSearchResultData>> {
        if(input.isBlank()) {
            return Result.Error(
                message = "Input is blank"
            )
        }

        return repository.getGroupByName(input)
    }
}