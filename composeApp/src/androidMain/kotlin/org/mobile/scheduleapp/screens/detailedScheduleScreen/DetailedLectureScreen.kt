package org.mobile.scheduleapp.screens.detailedScheduleScreen

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import org.mobile.scheduleapp.screens.ClassItem
import org.mobile.scheduleapp.screens.MyScheduleScreenLayout
import org.mobile.scheduleapp.screens.ScheduleItem
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

    MyScheduleScreenLayout(
        onCardClicked = {},
        onBackIconClicked = {},
        label = "",
        scheduleItems = scheduleItems
    )
}




@Preview(showBackground = true)
@Composable
private fun Preview() {
    ScheduleAppTheme {
        DetailedScheduleScreen()
    }
}