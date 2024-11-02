package org.mobile.scheduleapp.screens.detailedClassScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.mobile.scheduleapp.components.DetailsClassInfo
import org.mobile.scheduleapp.components.GroupCard
import org.mobile.scheduleapp.components.LectureCard
import org.mobile.scheduleapp.screens.detailedScheduleScreen.ClassItem
import org.mobile.scheduleapp.screens.detailedScheduleScreen.TopBar
import org.mobile.scheduleapp.screens.searchScreens.Group
import org.mobile.scheduleapp.screens.searchScreens.Lecturer
import org.mobile.scheduleapp.theming.Dimens
import org.mobile.scheduleapp.theming.ScheduleAppTheme

@Composable
fun DetailedClassScreen(modifier: Modifier = Modifier) {
    DetailedClassScreenLayout(
        onBackButtonClicked = {},
        onLectureCardClicked = {},
        onGroupCardClicked = {},
        label = "",
        isLecture = true
    )
}

@Composable
fun DetailedClassScreenLayout(
    modifier: Modifier = Modifier,
    onBackButtonClicked: () -> Unit,
    label: String,
    onGroupCardClicked: () -> Unit,
    onLectureCardClicked: () -> Unit,
    isLecture: Boolean
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
            .padding(horizontal = Dimens.LargeSpaceBetween)
    ) {
        item {
            TopBar(
                title = label,
                onBackIconClicked = onBackButtonClicked
            )
        }

        item { Spacer(modifier = Modifier.height(Dimens.LargeSpaceBetween)) }

        val lecture = Lecturer(
            fio = "Казанцева Ольга Геннадьевна",
            faculty = "ФПМИ",
            department = "МСС",
            imageUrl = "")

        val group = Group(
            course = 4,
            groupNum = 13,
            faculty = "ФПМИ",
            major = "ПИ"
        )

        item {
            if(isLecture)
                LectureCard(lecture = lecture, onLectureClicked = onLectureCardClicked)
            else
                GroupCard(group = group, onGroupClicked = onGroupCardClicked)
        }

        item { Spacer(modifier = Modifier.height(Dimens.LargeSpaceBetween)) }

        val classItem = ClassItem("8:15 - 9:35", "Л", "ОиМП", "605", date = "3 October")

        item { DetailsClassInfo(classItem = classItem) }
    }
    
}


@Preview
@Composable
private fun Preview() {
    ScheduleAppTheme {
        DetailedClassScreen()
    }
}