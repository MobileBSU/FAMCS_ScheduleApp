package org.mobile.scheduleapp.presentation.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.screens.searchScreens.groupSearchScreen.Group
import org.mobile.scheduleapp.presentation.view.theming.Dimens
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme

@Composable
fun GroupCard(
    modifier: Modifier = Modifier,
    group: Group,
    onGroupClicked: () -> Unit
) {

    Column(
        modifier = modifier
    ) {
        Text(
            text = stringResource(R.string.group),
            style = MaterialTheme.typography.bodyLarge
        )

        Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))
        GroupItem(
            modifier = modifier
                .clickable { onGroupClicked() }
                .background(
                    color = MaterialTheme.colorScheme.surface,
                    shape = RoundedCornerShape(Dimens.SmallCornerShape)
                ),
            group = group
        )
    }
}
