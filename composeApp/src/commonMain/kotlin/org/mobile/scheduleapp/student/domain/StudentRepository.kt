package org.mobile.scheduleapp.student.domain

import org.mobile.scheduleapp.common.util.Result

interface StudentRepository {
    suspend fun updateStudent(
        id: Long,
        name: String?,
        email: String?,
        password: String?,
        imageUrl: String?,
        groupId: Long?
    ): Result<StudentResultData>
    suspend fun getStudent(
        id: Long
    ): Result<StudentResultData>
}