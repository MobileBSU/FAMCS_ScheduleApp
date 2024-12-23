package org.mobile.scheduleapp.student.domain

import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.mobile.scheduleapp.common.util.Result

class UpdateStudentUseCase: KoinComponent {
    private val repository: StudentRepository by inject()

    suspend operator fun invoke(id: Long,
                                name: String?,
                                email: String?,
                                password: String?,
                                imageUrl: String?,
                                groupId: Long?) :
            Result<StudentResultData> {
        return repository.updateStudent(id, name, email, password, imageUrl, groupId)
    }
}