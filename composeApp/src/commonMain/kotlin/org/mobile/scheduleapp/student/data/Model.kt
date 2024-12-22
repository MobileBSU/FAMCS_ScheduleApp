package org.mobile.scheduleapp.student.data

import kotlinx.serialization.Serializable

@Serializable
internal data class UpdateStudentRequest(
    val name: String? = null,
    val email: String? = null,
    val password: String? = null,
    val imageUrl: String? = null,
    val groupId: Long? = null
)

@Serializable
internal data class StudentResponse(
    val data: StudentResponseData? = null,
    val errorMessage: String? = null
)

@Serializable
internal data class StudentResponseData(
    val id: Long,
    val name: String,
    val email: String,
    val imageUrl: String?,
    val password: String,
    val groupId: Long? = null
)