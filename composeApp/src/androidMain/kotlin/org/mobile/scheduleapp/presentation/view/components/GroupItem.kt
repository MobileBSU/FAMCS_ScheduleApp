package org.mobile.scheduleapp.screens.searchScreens

import androidx.compose.foundation.clickable
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
import org.mobile.scheduleapp.presentation.screens.searchScreens.groupSearchScreen.Group
import org.mobile.scheduleapp.presentation.view.theming.Dimens
import org.mobile.scheduleapp.presentation.view.theming.NeutralLightMedium
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme


@Composable
fun GroupItem(
    modifier: Modifier = Modifier,
    group: Group,
    onItemClicked: (Long) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = Dimens.MainHorizontalPadding)
            .clickable { onItemClicked(group.id) }
    ) {
        Text(
            text ="${group.groupNumber} группа" ,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Black,
            modifier = modifier.padding(top = Dimens.SmallVerticalPadding )
        )
        Row(
            horizontalArrangement = Arrangement.spacedBy(Dimens.LargeSpaceBetween),
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoText(text = "FAMCS")
            Dot()
            InfoText(text = group.name)
            Dot()
            InfoText(text = "Курс ${group.course}")
        }

        Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))

        Divider(color = NeutralLightMedium)
    }
}