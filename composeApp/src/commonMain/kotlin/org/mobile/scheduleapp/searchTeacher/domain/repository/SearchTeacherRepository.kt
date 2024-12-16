package org.mobile.scheduleapp.searchTeacher.domain.repository

import org.mobile.scheduleapp.searchTeacher.domain.model.TeacherSearchResultData
import org.mobile.scheduleapp.common.util.Result

internal interface SearchTeacherRepository {
    suspend fun getAllTeachers(): Result<List<TeacherSearchResultData>>

    suspend fun getTeacherByName(name: String): Result<List<TeacherSearchResultData>>

    suspend fun getTeacherById(id: Long): Result<List<TeacherSearchResultData>>
}