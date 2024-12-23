package org.mobile.scheduleapp.student.domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.mobile.scheduleapp.common.util.Result

class GetStudentUseCase: KoinComponent {
    private val repository: StudentRepository by inject()

    suspend operator fun invoke(id: Long): Result<StudentResultData> {
        return repository.getStudent(id)
    }
}