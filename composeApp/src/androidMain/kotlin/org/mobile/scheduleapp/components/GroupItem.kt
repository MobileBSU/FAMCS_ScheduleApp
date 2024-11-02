package org.mobile.scheduleapp.screens.searchScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.mobile.scheduleapp.theming.Dimens
import org.mobile.scheduleapp.theming.NeutralLightMedium
import org.mobile.scheduleapp.theming.NeutralLightWhite
import org.mobile.scheduleapp.theming.ScheduleAppTheme


@Composable
fun GroupItem(
    modifier: Modifier = Modifier,
    group: Group
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.MainHorizontalPadding)
    ) {
        Text(
            text ="${group.groupNum} группа" ,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Black,
            modifier = modifier.padding(top = Dimens.SmallVerticalPadding )
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.LargeSpaceBetween),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoText(text = group.faculty)
            Dot()
            InfoText(text = group.major)
            Dot()
            InfoText(text = "Курс ${group.course}")
        }

        Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))

        Divider(color = NeutralLightMedium)
    }
}


@Preview(showBackground = true)
@Composable
fun Prev(){
    ScheduleAppTheme {
        GroupItem(group = Group(course = 4, groupNum = 13, faculty = "ФПМИ", major = "ПИ"),
        )
    }
}
