package org.mobile.scheduleapp.screens.detailedScheduleScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun DaySchedule(
    scheduleItem: ScheduleItem,
    onCardClicked: () -> Unit
) {
    Column(modifier = Modifier.padding(vertical = 8.dp)) {
        Text(
            text = scheduleItem.day,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.padding(vertical = 4.dp)
        )

        Column {
            scheduleItem.classes.forEach { classItem ->
                ClassCard(
                    classItem = classItem,
                    onCardClicked = onCardClicked
                )
            }
        }
    }
}