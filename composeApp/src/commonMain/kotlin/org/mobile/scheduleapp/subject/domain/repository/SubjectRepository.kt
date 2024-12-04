package org.mobile.scheduleapp.subject.domain.repository

import SubjectResultData
import org.mobile.scheduleapp.common.util.Result

internal interface SubjectRepository {
    suspend fun getSubjectsByGroup(id: Long): Result<List<SubjectResultData>>
    suspend fun getSubjectsByTeacher(id: Long): Result<List<SubjectResultData>>
}