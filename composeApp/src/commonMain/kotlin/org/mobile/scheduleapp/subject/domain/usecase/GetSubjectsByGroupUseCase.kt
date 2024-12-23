package org.mobile.scheduleapp.subject.domain.usecase

import SubjectResultData
import kotlinx.datetime.Clock
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.TimeZone
import kotlinx.datetime.isoDayNumber
import kotlinx.datetime.plus
import kotlinx.datetime.toLocalDateTime
import kotlinx.datetime.todayIn
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.subject.domain.repository.SubjectRepository

class GetSubjectsByGroupUseCase: KoinComponent {
    private val repository: SubjectRepository by inject()

    suspend operator fun invoke(id: Long): Result<List<SubjectResultData>> {
        return repository.getSubjectsByGroup(id)
    }

}