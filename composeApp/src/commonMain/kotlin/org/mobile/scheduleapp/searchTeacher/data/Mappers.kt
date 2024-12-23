package org.mobile.scheduleapp.searchTeacher.data

import org.mobile.scheduleapp.searchGroup.data.GroupSearchResponseData
import org.mobile.scheduleapp.searchGroup.domain.model.GroupSearchResultData
import org.mobile.scheduleapp.searchTeacher.domain.model.TeacherSearchResultData

internal fun TeacherResponseData.toTeacherSearchResultData(): TeacherSearchResultData {
    return TeacherSearchResultData(id, name, bio, imageUrl)
}