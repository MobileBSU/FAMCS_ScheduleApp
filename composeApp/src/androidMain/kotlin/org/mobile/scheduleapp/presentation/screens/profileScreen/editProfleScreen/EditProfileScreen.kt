package org.mobile.scheduleapp.presentation.screens.profileScreen.editProfleScreen

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
import androidx.navigation.NavController
import coil3.compose.rememberAsyncImagePainter
import coil3.request.ImageRequest
import org.koin.androidx.compose.koinViewModel
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.screens.authScreens.login.CustomTextField
import org.mobile.scheduleapp.presentation.screens.profileScreen.ProfileController
import org.mobile.scheduleapp.presentation.screens.profileScreen.ProfileUiItem
import org.mobile.scheduleapp.presentation.screens.profileScreen.ProfileUiState
import org.mobile.scheduleapp.presentation.screens.profileScreen.ProfileViewModel
import org.mobile.scheduleapp.presentation.view.components.CustomButton
import org.mobile.scheduleapp.presentation.view.theming.Dimens
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme
import org.mobile.scheduleapp.screens.detailedScheduleScreen.TopBar

@Composable
fun EditProfileScreen(
    navController: NavController,
    modifier: Modifier = Modifier,
) {

    val viewModel: ProfileViewModel = koinViewModel()
    val state = viewModel.stateFlow.collectAsState().value


    EditProfileScreenLayout(
        modifier = modifier,
        state = state,
        controller = viewModel,
        onBackIconClicked = {
            navController.popBackStack()
        },
        onButtonClicked = {
            navController.popBackStack()
        }
    )
}

@Composable
fun EditProfileScreenLayout(
    modifier: Modifier = Modifier,
    state: ProfileUiState,
    controller: ProfileController,
    onBackIconClicked: () -> Unit,
    onButtonClicked: () -> Unit
) {
    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.GetContent()) { uri: Uri? ->
        uri?.let {
            controller.onSetNewPhotoClicked(uri.toString())
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(Dimens.MainHorizontalPadding),
    ) {
        TopBar(
            title = "Profile",
            onBackIconClicked = onBackIconClicked,
            isBackIconHidden = false
        )

        state.profileUiItem?.urlStr?.let {
            EditHeader(
                onSetNewPhotoClicked = {
                    launcher.launch("image/*")
                },
                uri = it
            )
        }

        Spacer(modifier = modifier.height(Dimens.MainHorizontalPadding))

        state.profileUiItem?.let { profile ->
            EditData(
                profile = profile,
                onNameChanged = controller::updateName,
                onEmailChanged = controller::updateEmail,
                onCourseChanged = controller::updateCourse,
                onGroupChanged = controller::updateGroup,
                onButtonClicked = {
                    onButtonClicked()
                    controller.onButtonClicked(
                        profile.id,
                        profile.name,
                        profile.email,
                        profile.course,
                        profile.group
                    )
                }
            )
        }
    }
}

@Composable
fun EditHeader(
    modifier: Modifier = Modifier,
    onSetNewPhotoClicked: () -> Unit,
    uri: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()

    ) {
        Image(
            painter = rememberAsyncImagePainter(
                model = ImageRequest.Builder(LocalContext.current)
                    .data(uri)
                    .build()
            ),
            contentDescription = null,
            modifier = modifier
                .size(Dimens.IconSize)
                .clip(RoundedCornerShape(Dimens.MediumCornerShape))
        )
        Spacer(modifier = modifier.height(Dimens.MainHorizontalPadding))

        Text(
            text = "Set New Photo",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.primary,
            modifier = modifier.clickable { onSetNewPhotoClicked() }
        )
    }

}

@Composable
fun EditData(
    modifier: Modifier = Modifier,
    profile: ProfileUiItem,
    onNameChanged: (String) -> Unit,
    onEmailChanged: (String) -> Unit,
    onCourseChanged: (String) -> Unit,
    onGroupChanged: (String) -> Unit,
    onButtonClicked: () -> Unit
) {
    var courseError by remember { mutableStateOf(false) }
    var groupError by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxHeight(),
    ) {
        EditInputTextField(
            value = profile.name,
            onValueChanged = onNameChanged,
            id = R.string.name
        )

        Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))

        EditInputTextField(
            value = profile.email,
            onValueChanged = onEmailChanged,
            id = R.string.email_address
        )

        Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))

        EditInputTextField(
            value = profile.course.toString(),
            onValueChanged = {
                courseError = it.toIntOrNull() == null
                onCourseChanged(it)
            },
            id = R.string.course
        )
        if (courseError) {
            Text(
                text = stringResource(id = R.string.invalid_course),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall
            )
        }

        Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))

        EditInputTextField(
            value = profile.group.toString(),
            onValueChanged = {
                groupError = it.toIntOrNull() == null
                onGroupChanged(it)
            },
            id = R.string.group
        )
        if (groupError) {
            Text(
                text = stringResource(id = R.string.invalid_group),
                color = MaterialTheme.colorScheme.error,
                style = MaterialTheme.typography.labelSmall
            )
        }

        Spacer(modifier = modifier.height(Dimens.MainVerticalPadding))

        CustomButton(
            modifier = modifier,
            onButtonClicked = {
                if (!courseError && !groupError) {
                    onButtonClicked()
                }
            },
            id = R.string.save
        )
    }
}


@Composable
fun EditInputTextField(
    modifier: Modifier = Modifier,
    value: String,
    @StringRes id : Int,
    onValueChanged: (String) -> Unit
) {
    Column {
        Text(
            text = stringResource(id = id),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )

        Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))

        CustomTextField(
            value = value,
            onValueChange = onValueChanged,
            hint = R.string.name,
        )
    }
}

