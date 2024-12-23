package org.mobile.scheduleapp.subject.domain.usecase

import SubjectResultData
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.subject.domain.repository.SubjectRepository

class GetSubjectByIdUseCase: KoinComponent {
    private val repository: SubjectRepository by inject()

    suspend operator fun invoke(id: Long): Result<List<SubjectResultData>> {
        return repository.getSubjectById(id)
    }
}