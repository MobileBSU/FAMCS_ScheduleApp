package org.mobile.scheduleapp.presentation.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.screens.searchScreens.groupSearchScreen.Group
import org.mobile.scheduleapp.presentation.view.theming.NeutralLightMedium
import org.mobile.scheduleapp.presentation.view.theming.NeutralLightWhite
import org.mobile.scheduleapp.screens.searchScreens.Divider
import org.mobile.scheduleapp.screens.searchScreens.Dot
import org.mobile.scheduleapp.screens.searchScreens.InfoText


@Composable
fun GroupList(
    modifier: Modifier = Modifier,
    groups: List<Group>
) {
    LazyColumn(modifier = Modifier.padding(8.dp)) {
        items(groups) { group ->
            org.mobile.scheduleapp.screens.searchScreens.GroupItem(
                modifier = modifier.background(NeutralLightWhite),
                group = group,
            )
        }
    }
}

@Composable
fun GroupItem(
    modifier: Modifier = Modifier,
    group: Group
) {
    Column(
        modifier = modifier
            .background(NeutralLightWhite)
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text ="${group.groupNumber} группа" ,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Black,
            modifier = modifier.padding(8.dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoText(text = "FAMCS")
            Dot()
            InfoText(text = group.name)
            Dot()
            InfoText(text = stringResource(R.string.course, group.course))
        }
        Divider(color = NeutralLightMedium)
    }
}