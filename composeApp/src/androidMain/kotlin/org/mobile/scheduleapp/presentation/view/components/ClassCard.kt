package org.mobile.scheduleapp.screens.detailedScheduleScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.mobile.scheduleapp.presentation.screens.detailedScheduleScreen.SubjectUiItem
import org.mobile.scheduleapp.presentation.view.theming.Dimens
import org.mobile.scheduleapp.presentation.view.theming.NeutralDarkDarkest
import org.mobile.scheduleapp.presentation.view.theming.NeutralDarkLightest

@Composable
fun ClassCard(
    classItem: SubjectUiItem,
    modifier: Modifier = Modifier,
    onCardClicked: () -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(vertical = Dimens.SmallVerticalPadding)
            .background(
                color = MaterialTheme.colorScheme.surface,
                shape = RoundedCornerShape(Dimens.SmallCornerShape)
            )
            .clickable { onCardClicked() }
            .padding(horizontal = Dimens.LargeSpaceBetween),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(Dimens.LargeSpaceBetween)
    ) {
        Text(
            text = classItem.startTime + " - " + classItem.endTime,
            style = MaterialTheme.typography.bodySmall,
            color = NeutralDarkDarkest
        )
        Text(
            text = stringResource(classItem.lectureType),
            style = MaterialTheme.typography.labelLarge,
            color = NeutralDarkDarkest
        )

        Column(
            modifier = Modifier.weight(1f).padding(start = 8.dp).padding(vertical = Dimens.SmallVerticalPadding)
        ) {
            Text(
                text = classItem.name,
                style = MaterialTheme.typography.bodySmall,
                color = NeutralDarkDarkest
            )
            Text(
                text = classItem.classRoom.toString(),
                style = MaterialTheme.typography.bodySmall,
                color = NeutralDarkDarkest
            )
        }

        classItem.groupId?.let {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Group icon",
                    modifier = Modifier.size(20.dp),
                    tint = NeutralDarkLightest
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = it.toString(),
                    color = NeutralDarkLightest,
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}