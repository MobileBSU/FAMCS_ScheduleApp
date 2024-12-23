package org.mobile.scheduleapp.presentation.screens.profileScreen

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import org.jetbrains.compose.resources.imageResource
import org.koin.androidx.compose.koinViewModel
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.view.navigation.AppRoute
import org.mobile.scheduleapp.presentation.view.theming.Dimens
import org.mobile.scheduleapp.presentation.view.theming.Dimens.SmallIconSize
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme
import org.mobile.scheduleapp.screens.detailedScheduleScreen.TopBar

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController
) {

    val viewModel: ProfileViewModel = koinViewModel()
    val state = viewModel.stateFlow.collectAsState().value

    ProfileScreenLayout(
        state = state,
        onEditClicked = {
            navController.navigate(AppRoute.EditScreen.route)
        },
        onNotificationClicked = {},
        onLanguageClicked = {},
        onPrivacyClicked = {},
        onStorageClicked = {  }
    )
}

@Composable
fun ProfileScreenLayout(
    state: ProfileUiState,
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

        state.profileUiItem?.let {
            ProfileHeader(
                profileUiItem = it,
                onEditClicked = onEditClicked
            )
        }
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
    profileUiItem: ProfileUiItem,
    onEditClicked: () -> Unit,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()
    ) {
        Box {
            if (profileUiItem.urlStr.isNullOrEmpty()) {
                Image(
                    painter = painterResource(R.drawable.ic_launcher_foreground),
                    contentDescription = null,
                    modifier = modifier
                        .size(Dimens.IconSize)
                        .clip(RoundedCornerShape(Dimens.MediumCornerShape))
                )
            } else {
                Image(
                    painter = rememberAsyncImagePainter(
                        model = ImageRequest.Builder(LocalContext.current)
                            .data(profileUiItem.urlStr)
                            .build()
                    ),
                    contentDescription = null,
                    modifier = modifier
                        .size(Dimens.IconSize)
                        .clip(RoundedCornerShape(Dimens.MediumCornerShape))
                        .border(width = 1.dp, color = MaterialTheme.colorScheme.primary)
                )
            }
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
        Text(profileUiItem.name, style = MaterialTheme.typography.displayMedium)
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

