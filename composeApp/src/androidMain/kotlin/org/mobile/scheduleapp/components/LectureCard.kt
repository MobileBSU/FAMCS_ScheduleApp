package org.mobile.scheduleapp.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.screens.searchScreens.Lecturer
import org.mobile.scheduleapp.theming.Dimens
import org.mobile.scheduleapp.theming.ScheduleAppTheme

@Composable
fun LectureCard(
    modifier: Modifier = Modifier,
    lecture: Lecturer,
    onLectureClicked: () -> Unit
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.lecture),
            style = MaterialTheme.typography.bodyLarge
        )

        Row(
            modifier = modifier
                .clickable { onLectureClicked() }
                .fillMaxWidth()
                .background(
                    color =  MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(Dimens.SmallCornerShape)),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Box(
                modifier = modifier
                    .padding(start = Dimens.SmallVerticalPadding)
                    .padding(vertical = Dimens.SmallVerticalPadding)
                    .background(
                        color = Color.Gray,
                        shape = CircleShape
                    )
                    .size(50.dp)
            ) {}

            Text(
                text = lecture.fio,
                style = MaterialTheme.typography.bodyLarge
            )

            Icon(
                modifier = modifier
                    .padding(end = Dimens.SmallVerticalPadding),
                painter = painterResource(R.drawable.right_button),
                contentDescription = "Next"
            )
        }
    }
}


@Preview
@Composable
private fun Preview() {

    val lecture = Lecturer(
        fio = "Казанцева Ольга Геннадьевна",
        faculty = "ФПМИ",
        department = "МСС",
        imageUrl = "")

    ScheduleAppTheme {
        LectureCard(
            lecture = lecture,
            onLectureClicked = {})
    }
}