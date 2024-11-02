package org.mobile.scheduleapp.screens.authScreens.signup

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.components.CustomButton
import org.mobile.scheduleapp.components.TermsAndCondRow
import org.mobile.scheduleapp.navigation.AppRoute
import org.mobile.scheduleapp.screens.authScreens.login.CustomTextField
import org.mobile.scheduleapp.theming.Dimens
import org.mobile.scheduleapp.theming.ScheduleAppTheme

@Composable
fun SignUpScreen(
    navController: NavController
) {
    SignUpLayout(
        uiState = SignUpScreenUiState(),
        onNameChange = {},
        onEmailChange = {},
        onPasswordChange = {},
        onPasswordConfirm = {},
        onTermsClicked = {},
        onSignUpButtonClicked = {
            navController.navigate(AppRoute.MySchedule.route) {
                popUpTo(AppRoute.Auth.route) { inclusive = true }
            }
        },
        onPrivacyClicked = {},
        onTermsAcceptedChange = {}
    )
}

@Composable
fun SignUpLayout(
    modifier: Modifier = Modifier,
    uiState: SignUpScreenUiState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirm: (String) -> Unit,
    onTermsAcceptedChange: (Boolean) -> Unit,
    onTermsClicked: () -> Unit,
    onPrivacyClicked: () -> Unit,
    onSignUpButtonClicked: () -> Unit
) {

    var isToggleCheckBox by remember {
        mutableStateOf(false)
    }

    Column (
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(Dimens.MainHorizontalPadding),
        verticalArrangement = Arrangement.Center,
    ){
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = stringResource(id = R.string.sign_up),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSecondary
            )

            Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))

            Text(
                text = stringResource(id = R.string.create_account_to_get_started),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface,
            )
        }

        Column(
            modifier = modifier
                .padding(vertical = Dimens.MainHorizontalPadding),
            verticalArrangement = Arrangement.spacedBy(Dimens.LargeSpaceBetween)
        ) {
            Column {
                Text(
                    text = stringResource(id = R.string.name),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )

                Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))
                
                CustomTextField(
                    value = uiState.name, 
                    onValueChange = onNameChange,
                    hint = R.string.name_example
                )
            }

            Column {
                Text(
                    text = stringResource(id = R.string.email_address),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )

                Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))

                CustomTextField(
                    value = uiState.email,
                    onValueChange = onEmailChange,
                    hint = R.string.email_example
                )
            }

            Column {
                Text(
                    text = stringResource(id = R.string.password),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSecondary
                )

                Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))

                CustomTextField(
                    value = uiState.password,
                    onValueChange = onPasswordChange,
                    hint = R.string.create_password,
                    isPasswordField = true
                )

                Spacer(modifier = modifier.height(Dimens.LargeSpaceBetween))

                CustomTextField(
                    value = uiState.password,
                    onValueChange = onPasswordConfirm,
                    hint = R.string.confirm_password,
                    isPasswordField = true
                )
            }

            TermsAndCondRow(
                onTermsClicked = onTermsClicked,
                onPrivacyClicked = onPrivacyClicked,
                isToggleCheckBox = uiState.isTermsAccepted,
                onTermsAcceptedChange = onTermsAcceptedChange
            )

            CustomButton(
                onButtonClicked = onSignUpButtonClicked,
                id = R.string.sign_up)
        }
    }

}

@Preview
@Composable
fun SignUpLayoutPreview() {
    ScheduleAppTheme {
        SignUpScreen(rememberNavController())
    }
}


data class SignUpScreenUiState(
    val name: String = "",
    val email: String = "",
    val password: String = "",
    val isTermsAccepted: Boolean = false
)