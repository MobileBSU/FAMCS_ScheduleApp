package org.mobile.scheduleapp.common.util

import org.mobile.scheduleapp.presentation.screens.searchScreens.groupSearchScreen.Group
import org.mobile.scheduleapp.presentation.screens.searchScreens.groupSearchScreen.GroupUiState
import org.mobile.scheduleapp.presentation.screens.searchScreens.lecturersSearchScreen.Lecturer
import org.mobile.scheduleapp.searchGroup.domain.model.GroupSearchResultData
import org.mobile.scheduleapp.searchTeacher.domain.model.TeacherSearchResultData

fun GroupSearchResultData.toUiState(): Group{
    return Group(
        course,
        groupNumber = groupNumber,
        name = name,
        subGroupNumber = subGroupNumber,
    )
}

fun TeacherSearchResultData.toUiState(): Lecturer {
    return Lecturer(
        id, name, bio, imageUrl
    )
}