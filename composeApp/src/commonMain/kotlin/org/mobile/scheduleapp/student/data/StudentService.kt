package org.mobile.scheduleapp.student.data

import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.setBody
import org.mobile.scheduleapp.common.data.KtorApi

internal class StudentService : KtorApi(){
    suspend fun getStudent(id: Long): StudentResponse = client.get {
        endPoint(path = "students/${id}")
    }.body()

    suspend fun updateStudent(id: Long, request: UpdateStudentRequest): StudentResponse = client.patch{
        setBody(request)
        endPoint(path = "students/${id}")
    }.body()
}