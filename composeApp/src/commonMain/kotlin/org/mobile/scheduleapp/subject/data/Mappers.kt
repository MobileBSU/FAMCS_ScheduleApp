package org.mobile.scheduleapp.subject.data

import SubjectResultData

internal fun SubjectResponseData.toSubjectResultData() : SubjectResultData {
    return SubjectResultData(id, name, dayOfWeek, startTime, endTime, classRoom, isLecture, teacherId, groupId)
}