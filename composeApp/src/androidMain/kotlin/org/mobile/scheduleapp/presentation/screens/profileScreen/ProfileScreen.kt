package org.mobile.scheduleapp.presentation.screens.profileScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.view.theming.Dimens
import org.mobile.scheduleapp.presentation.view.theming.Dimens.SmallIconSize
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme
import org.mobile.scheduleapp.screens.detailedScheduleScreen.TopBar


@Composable
fun ProfileScreen(
    navController: NavController
) {
    
}

@Composable
fun ProfileScreenLayout(
    modifier: Modifier = Modifier,
    onEditClicked: () -> Unit,
    onNotificationClicked: () -> Unit,
    onLanguageClicked: () -> Unit,
    onPrivacyClicked: () -> Unit,
    onStorageClicked: () -> Unit,
) {
    Column (
        modifier = modifier
            .fillMaxSize()
            .background(color = MaterialTheme.colorScheme.background)
    ){
        TopBar(
            title = stringResource(R.string.setting),
            onBackIconClicked = {}
        )

        Spacer(modifier = modifier.height(Dimens.LargeSpaceBetween))

        ProfileHeader(
            profile = Profile("Lucas", id = "@lucaas", urlStr = ""),
            onEditClicked = onEditClicked
        )
        Spacer(modifier = Modifier.height(Dimens.MainVerticalPadding))
        ProfileOption(
            option = "Notifications",
            onOptionClicked = onNotificationClicked)
        ProfileOption(
            option = "Language",
            onOptionClicked = onLanguageClicked)
        ProfileOption(
            option = "Privacy & Security",
            onOptionClicked = onPrivacyClicked)
        ProfileOption(
            option = "Storage",
            onOptionClicked = onStorageClicked)

    }
}

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    profile: Profile,
    onEditClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Box {
            Box(
//                painter = painterResource(id = R.drawable.ic_profile_placeholder),
//                contentDescription = "Profile Picture",
//                contentScale = ContentScale.Crop,
                modifier = modifier
                    .size(Dimens.IconSize)
                    .clip(RoundedCornerShape(Dimens.MediumCornerShape))
                    .background(Color.LightGray)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_edit),
                contentDescription = "Edit Icon",
                tint = Color.White,
                modifier = modifier
                    .size(SmallIconSize)
                    .align(Alignment.BottomEnd)
                    .background(Color.Blue, CircleShape)
                    .padding(4.dp)
                    .clickable { onEditClicked() }
            )
        }
        Spacer(modifier = modifier.height(Dimens.MainHorizontalPadding))
        Text(profile.name, style = MaterialTheme.typography.displayMedium)
    }
}

@Composable
fun ProfileOption(
    modifier: Modifier = Modifier,
    option: String,
    onOptionClicked: () -> Unit
) {
    Column {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp)
                .clickable { onOptionClicked() },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(option, style = MaterialTheme.typography.labelMedium)
            Spacer(modifier = modifier.weight(1f))
            Icon(
                painter = painterResource(id = R.drawable.right_button),
                contentDescription = "Arrow Icon"
            )
        }
        HorizontalDivider(thickness = 0.5.dp, color = Color.LightGray)
    }
}

data class Profile(val name: String, val id: String, val urlStr: String)

@Preview
@Composable
private fun Prev() {
    ScheduleAppTheme {
        ProfileScreenLayout(
            onEditClicked = {},
            onNotificationClicked = {},
            onPrivacyClicked = {},
            onStorageClicked = {},
            onLanguageClicked = {}
        )
    }
}