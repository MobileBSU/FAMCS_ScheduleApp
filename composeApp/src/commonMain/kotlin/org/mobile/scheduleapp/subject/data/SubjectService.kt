package org.mobile.scheduleapp.subject.data

import io.ktor.client.call.body
import io.ktor.client.request.get
import org.mobile.scheduleapp.common.data.KtorApi

internal class SubjectService: KtorApi() {

    suspend fun getSubjectsByGroup(id: Long): SubjectResponse = client.get {
        endPoint(path = "subject/group/${id}")
    }.body()

    suspend fun getSubjectsByTeacher(id: Long): SubjectResponse = client.get {
        endPoint(path = "subject/teacher/${id}")
    }.body()
}