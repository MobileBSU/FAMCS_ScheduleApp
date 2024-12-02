package org.mobile.scheduleapp.presentation.screens.profileScreen.editProfleScreen

import androidx.annotation.StringRes
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.screens.authScreens.login.CustomTextField
import org.mobile.scheduleapp.presentation.view.components.CustomButton
import org.mobile.scheduleapp.presentation.view.theming.Dimens
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme
import org.mobile.scheduleapp.screens.detailedScheduleScreen.TopBar

@Composable
fun EditProfileScreen(navController: NavController) {

}

@Composable
fun EditProfileScreenLayout(
    modifier: Modifier = Modifier,
    onSetNewPhotoClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(Dimens.MainHorizontalPadding),
    ) {
        TopBar(
            title = "Profile",
            onBackIconClicked = {},
            isBackIconHidden = false
        )

        EditHeader(
            onSetNewPhotoClicked = onSetNewPhotoClicked
        )

        Spacer(modifier = modifier.height(Dimens.MainHorizontalPadding))

        val profileUi = ProfileUi(name = "Lucas", surname = "Scot", email = "lucas@scot")
        EditData(
            profile = profileUi,
            onNameChanged = {},
            onSurnameChanged = {}
        )
    }

}

@Composable
fun EditHeader(
    modifier: Modifier = Modifier,
    onSetNewPhotoClicked: () -> Unit
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier.fillMaxWidth()

    ) {
        Box(
//                painter = painterResource(id = R.drawable.ic_profile_placeholder),
//                contentDescription = "Profile Picture",
//                contentScale = ContentScale.Crop,
            modifier = modifier
                .size(Dimens.IconSize)
                .clip(RoundedCornerShape(Dimens.MediumCornerShape))
                .background(Color.LightGray)
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
    profile: ProfileUi,
    onNameChanged: (String) -> Unit,
    onSurnameChanged: (String) -> Unit

) {
    Column(
        modifier = modifier.fillMaxHeight(),
    ) {
        EditInputTextField(
            value = profile.name,
            onValueChanged = onNameChanged,
            id = R.string.name
        )

        Spacer(modifier= modifier.height(Dimens.SmallVerticalPadding))

        EditInputTextField(
            value = profile.surname?: "",
            onValueChanged = onSurnameChanged,
            id = R.string.surname
        )

        Spacer(modifier= modifier.height(Dimens.SmallVerticalPadding))

        EditInputTextField(
            value = profile.email,
            onValueChanged = onSurnameChanged,
            id = R.string.email_address
        )

        Spacer(modifier= modifier.height(Dimens.SmallVerticalPadding))

        Text(
            text = stringResource(id = R.string.course),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))

        DropdownSelection(
            label = "Select Course",
            options = listOf("1", "2", "3", "4")
        )

        Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))

        Text(
            text = stringResource(id = R.string.group),
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSecondary
        )
        Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))

        DropdownSelection(
            label = "Select Group",
            options = listOf("1","2","3","4","5","6","7","8","9","10","11","12","13")
        )

        Spacer(modifier = modifier.height(Dimens.MainVerticalPadding))

        CustomButton(
            modifier = modifier,
            onButtonClicked = {},
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropdownSelection(label: String, options: List<String>) {
    var expanded by remember { mutableStateOf(false) }
    var selectedOption by remember { mutableStateOf(options[0]) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        CustomTextField(
            value = selectedOption,
            onValueChange = {},
            hint =R.string.empty
        )
        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            options.forEach { selectionOption ->
                DropdownMenuItem(
                    onClick = {
                        selectedOption = selectionOption
                        expanded = false
                    },
                    text ={ Text(text = selectionOption)}
                )
            }
        }
    }
}

data class ProfileUi(val name: String, val surname: String?, val email: String)

@Preview
@Composable
private fun Preview() {
    ScheduleAppTheme{
        EditProfileScreenLayout(
            onSetNewPhotoClicked = {}
        )
    }
}
