package org.mobile.scheduleapp.student.data

import kotlinx.coroutines.withContext
import org.mobile.scheduleapp.common.util.DispatcherProvider
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.student.domain.StudentRepository
import org.mobile.scheduleapp.student.domain.StudentResultData

internal class StudentRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val studentService: StudentService
) : StudentRepository {
    override suspend fun updateStudent(
        id: Long,
        name: String?,
        email: String?,
        password: String?,
        imageUrl: String?,
        groupId: Long?
    ): Result<StudentResultData> {
        return withContext(dispatcher.io) {
            try {

                val request = UpdateStudentRequest(name,email,password, imageUrl, groupId)

                val response = studentService.updateStudent(id, request)

                if (response.data == null ){
                    Result.Error(
                        message = response.errorMessage!!
                    )
                }else {
                    Result.Success(
                        data = response.data.toStudentResult()
                    )
                }
            } catch (e: Exception) {
                Result.Error(
                    message = "Oops, we couldn`t send your request + $e"
                )
            }
        }
    }

    override suspend fun getStudent(id: Long):Result<StudentResultData> {
        return withContext(dispatcher.io) {
            try {

                val response = studentService.getStudent(id)

                if (response.data == null ){
                    Result.Error(
                        message = response.errorMessage!!
                    )
                }else {
                    Result.Success(
                        data = response.data.toStudentResult()
                    )
                }
            } catch (e: Exception) {
                Result.Error(
                    message = "Oops, we couldn`t send your request + $e"
                )
            }
        }
    }
}