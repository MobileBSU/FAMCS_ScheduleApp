package org.mobile.scheduleapp.searchTeacher.domain.usecase

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.searchTeacher.domain.model.TeacherSearchResultData
import org.mobile.scheduleapp.searchTeacher.domain.repository.SearchTeacherRepository

class GetTeacherByIdUseCase : KoinComponent {
    private val repository: SearchTeacherRepository by inject()

    suspend operator fun invoke(id: Long): Result<List<TeacherSearchResultData>> {
        return repository.getTeacherById(id)
    }
}