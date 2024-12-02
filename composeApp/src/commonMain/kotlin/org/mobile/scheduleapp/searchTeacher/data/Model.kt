package org.mobile.scheduleapp.searchTeacher.data

import kotlinx.serialization.Serializable

@Serializable
internal data class TeacherResponse(
    val data: List<TeacherResponseData>? = null,
    val errorMessage: String? = null
)

@Serializable
internal data class TeacherResponseData (
    val id: Long,
    val name: String,
    val bio: String,
    val imageUrl: String?
)