package org.mobile.scheduleapp.presentation.screens.authScreens.signup

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.koin.androidx.compose.koinViewModel
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.view.components.CustomButton
import org.mobile.scheduleapp.presentation.view.components.TermsAndCondRow
import org.mobile.scheduleapp.presentation.view.navigation.AppRoute
import org.mobile.scheduleapp.presentation.screens.authScreens.login.CustomTextField
import org.mobile.scheduleapp.presentation.view.theming.Dimens
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme

@Composable
fun SignUpScreen(
    navController: NavController
) {

    val viewModel: SignUpViewModel = koinViewModel()


    SignUpLayout(
        uiState = viewModel.state,
        onNameChange = viewModel::updateUsername,
        onEmailChange = viewModel::updateEmail,
        onPasswordChange = viewModel::updatePassword,
        onPasswordConfirm = {},
        onTermsClicked = {},
        onSignUpButtonClicked = viewModel::signUp,
//        {
//
//            navController.navigate(AppRoute.MySchedule.route) {
//                popUpTo(AppRoute.Auth.route) { inclusive = true }
//            }
//        },
        onPrivacyClicked = {},
        onTermsAcceptedChange = viewModel::updateIsTermsAccepted,
        onNavigateToHome = {},
        onNavigateToLogin = {}
    )
}

@Composable
fun SignUpLayout(
    modifier: Modifier = Modifier,
    uiState: SignUpState,
    onNameChange: (String) -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPasswordConfirm: (String) -> Unit,
    onTermsAcceptedChange: (Boolean) -> Unit,
    onTermsClicked: () -> Unit,
    onPrivacyClicked: () -> Unit,
    onNavigateToLogin: () -> Unit,
    onSignUpButtonClicked: () -> Unit,
    onNavigateToHome: () -> Unit
) {

    val context = LocalContext.current
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
                        value = uiState.username,
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

        if (uiState.isAuthenticating) {
            CircularProgressIndicator()
        }
    }

    LaunchedEffect(
        key1 = uiState.authenticationSucceed,
        key2 = uiState.authErrorMessage,
        block =  {
            if(uiState.authenticationSucceed) {
                onNavigateToHome()
            }

            if (uiState.authErrorMessage != null) {
                Toast.makeText(context, uiState.authErrorMessage, Toast.LENGTH_SHORT).show()
            }
    })

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