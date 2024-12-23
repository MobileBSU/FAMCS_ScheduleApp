package org.mobile.scheduleapp.student.domain

data class StudentResultData(
    val id: Long,
    val name: String,
    val imageUrl: String? = null,
    val email: String,
    val groupId: Long? = null
)