package org.mobile.scheduleapp.searchGroup.domain.usecase

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.searchGroup.domain.model.GroupSearchResultData
import org.mobile.scheduleapp.searchGroup.domain.repository.SearchGroupRepository

class GetGroupByCourseUseCase: KoinComponent {
    private val repository: SearchGroupRepository by inject()

    suspend operator fun invoke(course: Int, group: Int): Result<List<GroupSearchResultData>> {
        return repository.getGroupByCourse(course, group)
    }
}