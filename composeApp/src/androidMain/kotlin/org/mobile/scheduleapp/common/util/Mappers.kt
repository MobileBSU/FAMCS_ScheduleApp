package org.mobile.scheduleapp.common.util

import SubjectResultData
import android.os.Build
import androidx.annotation.RequiresApi
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.screens.detailedClassScreen.DetailedClassUiState
import org.mobile.scheduleapp.presentation.screens.detailedClassScreen.DetailedSubjectUiItem
import org.mobile.scheduleapp.presentation.screens.detailedScheduleScreen.ScheduleUiState
import org.mobile.scheduleapp.presentation.screens.detailedScheduleScreen.SubjectUiItem
import org.mobile.scheduleapp.presentation.screens.detailedScheduleScreen.SubjectsUi
import org.mobile.scheduleapp.presentation.screens.searchScreens.groupSearchScreen.Group
import org.mobile.scheduleapp.presentation.screens.searchScreens.lecturersSearchScreen.Lecturer
import org.mobile.scheduleapp.searchGroup.domain.model.GroupSearchResultData
import org.mobile.scheduleapp.searchTeacher.domain.model.TeacherSearchResultData
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import java.time.temporal.WeekFields
import java.util.Locale

fun GroupSearchResultData.toUiState(): Group{
    return Group(
        id,
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
@RequiresApi(Build.VERSION_CODES.O)
fun List<SubjectResultData>.toScheduleUiState(id: Long): List<ScheduleUiState> {
    val today = LocalDate.now()
    val weekFields = WeekFields.of(Locale.getDefault())
    val currentDayOfWeek = today.get(weekFields.dayOfWeek()) // 1 - Понедельник, 7 - Воскресенье

    return this.groupBy { it.dayOfWeek }.map { (dayOfWeek, subjects) ->
        val daysUntil = if (dayOfWeek >= currentDayOfWeek) {
            dayOfWeek - currentDayOfWeek
        } else {
            7 - (currentDayOfWeek - dayOfWeek)
        }

        val targetDate = today.plus(daysUntil.toLong(), ChronoUnit.DAYS)
        val dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")

        val uiSubjectsItems = subjects.map { subject ->
            SubjectUiItem(
                id = subject.id,
                name = subject.name,
                startTime = subject.startTime,
                endTime = subject.endTime,
                classRoom = subject.classRoom,
                groupId = subject.groupId,
                teacherId = subject.teacherId,
                lectureType = if(subject.isLecture) R.string.L else R.string.P,
            )
        }

        val uiSubjects = subjects.map {
            SubjectsUi(
                day = targetDate.dayOfWeek.name.lowercase()
                    .replaceFirstChar { it.uppercase() },
                date = targetDate.format(dateFormatter),
                subjects = uiSubjectsItems
            )
        }

        ScheduleUiState(
            id = id,
            list = uiSubjects
        )
    }
}

fun List<SubjectResultData>.toSubject(): DetailedClassUiState {

    val subject: SubjectResultData = this.first()

    val day: Int = when(subject.dayOfWeek){
        1 -> R.string.monday
        2 -> R.string.tuesday
        3 -> R.string.wednesday
        4 -> R.string.thursday
        5 -> R.string.friday
        6 -> R.string.saturday
        else -> {R.string.na}
    }

    val subjectUiItem = DetailedSubjectUiItem(
        name = subject.name,
        day = day,
        startTime = subject.startTime,
        endTime = subject.endTime,
        classRoom = subject.classRoom,
        lectureType = if(subject.isLecture) R.string.lecture else R.string.practice
    )


    return DetailedClassUiState(
        id = subject.id,
        teacherId = subject.id,
        groupId = subject.groupId,
        subjectUiItem = subjectUiItem
    )
}