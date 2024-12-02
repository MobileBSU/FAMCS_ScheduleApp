package org.mobile.scheduleapp.searchTeacher.data

import io.ktor.client.call.body
import io.ktor.client.request.get
import org.mobile.scheduleapp.common.data.KtorApi

internal class SearchTeacherService: KtorApi() {

    suspend fun getAllTeachers(): TeacherResponse = client.get{
        endPoint(path = "teachers")
    }.body()

    suspend fun getTeacherByName(name: String): TeacherResponse = client.get {
        endPoint(path = "teachers/${name}")
    }.body()
}