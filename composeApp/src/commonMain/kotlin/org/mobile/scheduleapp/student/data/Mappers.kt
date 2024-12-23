package org.mobile.scheduleapp.student.data

import org.mobile.scheduleapp.student.domain.StudentResultData

internal fun StudentResponseData.toStudentResult(): StudentResultData {
    return StudentResultData(id, name, imageUrl, email, groupId)
}