package org.mobile.scheduleapp.subject.data

import SubjectResultData
import kotlinx.coroutines.withContext
import org.mobile.scheduleapp.common.util.DispatcherProvider
import org.mobile.scheduleapp.common.util.Result
import org.mobile.scheduleapp.subject.domain.repository.SubjectRepository

internal class SubjectRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val subjectService: SubjectService
): SubjectRepository {
    override suspend fun getSubjectsByGroup(id: Long): Result<List<SubjectResultData>> {
        return withContext(dispatcher.io) {
            try {
                val subjectResponse = subjectService.getSubjectsByGroup(id)

                if(subjectResponse.data == null) {
                    Result.Error(
                        message = subjectResponse.errorMessage!!
                    )
                } else {
                    Result.Success(
                        data = subjectResponse.data.map { it.toSubjectResultData()}
                    )
                }
            } catch (e: Exception) {
                Result.Error(
                    message = e.message
                )
            }
        }
    }

    override suspend fun getSubjectsByTeacher(id: Long): Result<List<SubjectResultData>> {
        return withContext(dispatcher.io) {
            try {
                val subjectResponse = subjectService.getSubjectsByTeacher(id)

                if(subjectResponse.data == null) {
                    Result.Error(
                        message = subjectResponse.errorMessage!!
                    )
                } else {
                    Result.Success(
                        data = subjectResponse.data.map { it.toSubjectResultData()}
                    )
                }
            } catch (e: Exception) {
                Result.Error(
                    message = e.message
                )
            }
        }
    }

    override suspend fun getSubjectById(id: Long): Result<List<SubjectResultData>> {
        return withContext(dispatcher.io) {
            try {
                val subjectResponse = subjectService.getSubjectById(id)

                if(subjectResponse.data == null) {
                    Result.Error(
                        message = subjectResponse.errorMessage!!
                    )
                } else {
                    Result.Success(
                        data = subjectResponse.data.map { it.toSubjectResultData()}
                    )
                }
            } catch (e: Exception) {
                Result.Error(
                    message = e.message
                )
            }
        }
    }
}