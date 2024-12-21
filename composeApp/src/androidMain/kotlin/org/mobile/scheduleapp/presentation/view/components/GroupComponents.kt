package org.mobile.scheduleapp.presentation.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.screens.searchScreens.groupSearchScreen.Group
import org.mobile.scheduleapp.presentation.view.theming.Dimens
import org.mobile.scheduleapp.presentation.view.theming.NeutralLightMedium
import org.mobile.scheduleapp.presentation.view.theming.NeutralLightWhite
import org.mobile.scheduleapp.screens.searchScreens.Divider
import org.mobile.scheduleapp.screens.searchScreens.Dot
import org.mobile.scheduleapp.screens.searchScreens.GroupItem
import org.mobile.scheduleapp.screens.searchScreens.InfoText


@Composable
fun GroupList(
    modifier: Modifier = Modifier,
    groups: List<Group>,
    onItemClicked: (Long) -> Unit
) {

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        itemsIndexed(groups) { index, group ->
            val shape = when(index) {
                0 -> RoundedCornerShape(topStart = Dimens.SmallCornerShape, topEnd = Dimens.SmallCornerShape)
                groups.size - 1 -> RoundedCornerShape(bottomStart = Dimens.SmallCornerShape, bottomEnd = Dimens.SmallCornerShape)
                else -> RoundedCornerShape(0)
            }
            GroupItem(
                modifier = modifier.background(NeutralLightWhite, shape),
                group = group,
                onItemClicked = onItemClicked
            )
        }
    }


}
