package org.mobile.scheduleapp.searchTeacher.data

import kotlinx.coroutines.withContext
import org.mobile.scheduleapp.common.util.DispatcherProvider
import org.mobile.scheduleapp.searchTeacher.domain.model.TeacherSearchResultData
import org.mobile.scheduleapp.searchTeacher.domain.repository.SearchTeacherRepository
import org.mobile.scheduleapp.common.util.Result

internal class SearchTeacherRepositoryImpl(
    private val dispatcher: DispatcherProvider,
    private val searchTeacherService: SearchTeacherService
) : SearchTeacherRepository {
    override suspend fun getAllTeachers(): Result<List<TeacherSearchResultData>> {
        return withContext(dispatcher.io) {
            try {
                val teacherSearchResponse = searchTeacherService.getAllTeachers()

                if (teacherSearchResponse.data == null) {
                    Result.Error(
                        message = teacherSearchResponse.errorMessage!!
                    )
                } else {
                    Result.Success(
                        data = teacherSearchResponse.data.map { it.toTeacherSearchResultData() }
                    )
                }
            } catch (e: Exception) {
                Result.Error(
                    message = e.message
                )
            }
        }
    }

    override suspend fun getTeacherByName(name: String): Result<List<TeacherSearchResultData>> {
        return withContext(dispatcher.io) {
            try {
                val teacherSearchResponse = searchTeacherService.getTeacherByName(name)

                if (teacherSearchResponse.data == null) {
                    Result.Error(
                        message = teacherSearchResponse.errorMessage!!
                    )
                } else {
                    Result.Success(
                        data = teacherSearchResponse.data.map { it.toTeacherSearchResultData() }
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