package org.mobile.scheduleapp.searchTeacher.domain.model

data class TeacherSearchResultData(
    val id: Long,
    val name: String,
    val bio: String,
    val imageUrl: String?
) {
}