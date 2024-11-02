package org.mobile.scheduleapp.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import org.mobile.scheduleapp.screens.detailedScheduleScreen.DaySchedule
import org.mobile.scheduleapp.screens.detailedScheduleScreen.TopBar
import org.mobile.scheduleapp.theming.Dimens

@Composable
fun MyScheduleScreen(
    navController: NavController
) {

}

@Composable
fun MyScheduleScreenLayout(
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

