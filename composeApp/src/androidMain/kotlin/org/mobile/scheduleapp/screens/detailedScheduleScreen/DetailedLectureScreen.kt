package org.mobile.scheduleapp.screens.detailedScheduleScreen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.mobile.scheduleapp.theming.Dimens
import org.mobile.scheduleapp.theming.ScheduleAppTheme

@Composable
fun DetailedScheduleScreen(modifier: Modifier = Modifier) {
    val scheduleItems = listOf(
        ScheduleItem("Mon, 1 October", listOf(
            ClassItem("8:15 - 9:35", "Л", "ОиМП", "605", date = "1 October"),
            ClassItem("9:45 - 11:05", "П", "ОиМП", "600-в", group = 1, date = "1 October"),
            ClassItem("11:15 - 12:35", "П", "ОиМП", "600-в", group = 1, date = "1 October"),
            ClassItem("13:00 - 14:20", "Л", "ОиМП", "605", date = "1 October")
        )),
        ScheduleItem("Tue, 2 October", listOf(
            ClassItem("8:15 - 9:35", "Л", "ОиМП", "605", date = "2 October"),
            ClassItem("9:45 - 11:05", "П", "ОиМП", "600-в", group = 1, date = "2 October"),
            ClassItem("11:15 - 12:35", "П", "ОиМП", "600-в", group = 1, date = "2 October"),
            ClassItem("13:00 - 14:20", "Л", "ОиМП", "605", date = "2 October")
        )),
        ScheduleItem("Wed, 3 October", listOf(
            ClassItem("8:15 - 9:35", "Л", "ОиМП", "605", date = "3 October")
        ))
    )

    DetailedScheduleScreenLayout(
        onCardClicked = {},
        onBackIconClicked = {},
        label = "",
        scheduleItems = scheduleItems
    )
}

@Composable
fun DetailedScheduleScreenLayout(
    modifier: Modifier = Modifier,
    onBackIconClicked: () -> Unit,
    onCardClicked: () -> Unit,
    label: String,
    scheduleItems: List<ScheduleItem>
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.LargeSpaceBetween)
    ) {
        item {
            TopBar(
                title = label,
                onBackIconClicked = onBackIconClicked
            )
        }

        item { Spacer(modifier = Modifier.height(Dimens.LargeSpaceBetween)) }

        items(scheduleItems.size) { index ->
            DaySchedule(
                scheduleItems[index],
                onCardClicked
            )
        }
    }
}



data class ScheduleItem(val day: String, val classes: List<ClassItem>)
data class ClassItem(val time: String, val type: String,val name : String, val room: String, val group: Int? = null, val date:String)





@Preview(showBackground = true)
@Composable
private fun Preview() {
    ScheduleAppTheme {
        DetailedScheduleScreen()
    }
}