package org.mobile.scheduleapp.presentation.view.components

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.screens.detailedClassScreen.DetailedSubjectUiItem
import org.mobile.scheduleapp.screens.searchScreens.Divider
import org.mobile.scheduleapp.presentation.view.theming.Dimens
import org.mobile.scheduleapp.presentation.view.theming.NeutralDarkLightest
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme

@Composable
fun DetailsClassInfo(
    modifier: Modifier = Modifier,
    classItem: DetailedSubjectUiItem
) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.details),
            style = MaterialTheme.typography.bodyLarge
        )

        Column(
            modifier = modifier
                .fillMaxWidth()
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(Dimens.SmallCornerShape)
                )
        ) {
            Text(
                modifier = modifier.padding(Dimens.DetailedPadding),
                text = classItem.name,
                style = MaterialTheme.typography.displayMedium,
            )

            Divider(color = Color.Black)

            InfoRow(
                label = R.string.time,
                text = classItem.startTime + " - " + classItem.endTime
            )
            InfoRow(
                label = R.string.date,
                text = stringResource(classItem.day)
            )
            InfoRow(
                label = R.string.type,
                text = stringResource(classItem.lectureType)
            )

            InfoRow(
                label = R.string.room,
                text = classItem.classRoom.toString()
            )

        }
    }
}

@Composable
fun InfoRow(
    modifier: Modifier = Modifier,
    @StringRes label : Int,
    text: String
    ) {
    Row(
        modifier = modifier.fillMaxWidth().padding(Dimens.DetailedPadding),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = stringResource(label),
            style = MaterialTheme.typography.bodySmall,
            color = Color.Black
        )

        Text(
            text = text,
            style = MaterialTheme.typography.bodySmall,
            color = NeutralDarkLightest
        )
    }

    if (label != R.string.room) {
        Divider(
            modifier.padding(start = Dimens.DetailedPadding),
            color = Color.Gray)
    }
}

@Preview
@Composable
private fun Preview() {

}