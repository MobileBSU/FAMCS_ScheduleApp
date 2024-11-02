package org.mobile.scheduleapp.screens.detailedClassScreen

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.unit.dp
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.screens.ClassItem
import org.mobile.scheduleapp.screens.searchScreens.Divider
import org.mobile.scheduleapp.theming.Dimens
import org.mobile.scheduleapp.theming.NeutralDarkLightest
import org.mobile.scheduleapp.theming.ScheduleAppTheme

@Composable
fun DetailsClassInfo(
    modifier: Modifier = Modifier,
    classItem: ClassItem
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
                text = classItem.time
            )
            InfoRow(
                label = R.string.date,
                text = classItem.date
            )
            InfoRow(
                label = R.string.type,
                text = classItem.type
            )
            InfoRow(
                label = R.string.group,
                text = if (classItem.group == null) stringResource(R.string.full) else classItem.group.toString()
            )
            InfoRow(
                label = R.string.room,
                text = classItem.room
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

    val classItem = ClassItem("8:15 - 9:35", "Л", "ОиМП", "605", date = "3 October")

    ScheduleAppTheme {
        DetailsClassInfo(classItem = classItem)
    }
}