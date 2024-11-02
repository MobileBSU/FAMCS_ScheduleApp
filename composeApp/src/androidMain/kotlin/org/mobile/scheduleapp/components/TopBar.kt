package org.mobile.scheduleapp.screens.detailedScheduleScreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.components.BackButton
import org.mobile.scheduleapp.theming.Dimens

@Composable
fun TopBar(
    modifier: Modifier = Modifier,
    title: String,
    onBackIconClicked: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = Dimens.LargeSpaceBetween)
    ) {
        BackButton(
            modifier = modifier.align(Alignment.CenterStart),
            icon = painterResource(R.drawable.left_button),
            onNavigateClick = onBackIconClicked
        )

        Text(
            modifier = modifier
                .align(Alignment.Center),
            text = title,
            style = MaterialTheme.typography.displaySmall
        )
    }
}
