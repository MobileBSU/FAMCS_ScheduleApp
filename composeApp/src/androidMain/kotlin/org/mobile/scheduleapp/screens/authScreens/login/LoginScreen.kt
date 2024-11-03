package org.mobile.scheduleapp.screens.authScreens.login

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.navigation.AppRoute
import org.mobile.scheduleapp.theming.Dimens
import org.mobile.scheduleapp.theming.NeutralLightDark
import org.mobile.scheduleapp.theming.ScheduleAppTheme

@Composable
fun LoginScreen(
    navController: NavController
) {

    LoginLayout(
        uiState = LoginUiState(),
        onEmailChange = {},
        onPasswordChange = {},
        onForgotPasswordClicked = {},
        onButtonLoginClicked = {
            navController.navigate(AppRoute.Main.route) {
                popUpTo(AppRoute.Auth.route) {inclusive = true}
            }
        },
        onRegisterClicked = {
            navController.navigate(AppRoute.SignUp.route)
        }
    )
}

@Composable
fun LoginLayout(
    modifier: Modifier = Modifier,
    uiState: LoginUiState,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onForgotPasswordClicked: (String) -> Unit,
    onButtonLoginClicked: () -> Unit,
    onRegisterClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Box(
            modifier = modifier
                .fillMaxHeight(0.4f)
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.secondary)
        )

        Column(
            modifier = modifier
                .padding(
                    vertical = Dimens.MainVerticalPadding,
                    horizontal = Dimens.MainHorizontalPadding
                )
        ) {
            Text(
                text = stringResource(id = R.string.welcome),
                style = MaterialTheme.typography.displayLarge,
                modifier = modifier
                    .align(Alignment.Start)
            )

            Column(
                modifier = modifier
                    .padding(vertical = Dimens.MainHorizontalPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(Dimens.LargeSpaceBetween)
            ) {
                CustomTextField(
                    value = uiState.email,
                    onValueChange = onEmailChange,
                    hint = R.string.email_address
                )
                CustomTextField(
                    value = uiState.password,
                    onValueChange = onPasswordChange,
                    hint = R.string.password,
                    isPasswordField = true
                )

                Text(
                    text = stringResource(id = R.string.forgot_password),
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = modifier
                        .align(Alignment.Start)
                        .clickable {
                            onForgotPasswordClicked(uiState.email)
                        }
                )
            }
            Column(
                modifier = modifier
            ) {
                Button(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(Dimens.ButtonSize),
                    shape = MaterialTheme.shapes.medium,
                    colors = ButtonDefaults.buttonColors(
                        containerColor = MaterialTheme.colorScheme.primary,
                        contentColor = MaterialTheme.colorScheme.onPrimary
                    ),
                    onClick = onButtonLoginClicked
                ) {
                    Text(
                        stringResource(id = R.string.login),
                        style = MaterialTheme.typography.bodyMedium,
                        )
                }

                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = Dimens.LargeSpaceBetween),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = stringResource(id = R.string.not_a_memeber),
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = modifier.width(5.dp))
                    Text(
                        text = stringResource(id = R.string.register),
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = modifier
                            .clickable { onRegisterClicked() }
                    )

                }
            }



        }

    }

}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordField: Boolean = false,
    isEditField: Boolean = false,
    isSingleLine: Boolean = false,
    @StringRes hint: Int,
    isHintHidden: Boolean = false,
    trailingIcon: @Composable (() -> Unit)? = null,
    readOnly: Boolean = false
) {

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.medium)
            .border(
                width = 1.dp,
                color = NeutralLightDark,
                shape = MaterialTheme.shapes.medium
            ),
        value = value,
        readOnly = readOnly,
        onValueChange = onValueChange,
        placeholder = {
            if(!isHintHidden) {
                Text(
                    text = stringResource(id = hint),
                    style = MaterialTheme.typography.labelMedium
                )
            }
        },
        textStyle = MaterialTheme.typography.labelMedium,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        singleLine = isSingleLine,
        colors = TextFieldDefaults.colors(
            focusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedContainerColor = MaterialTheme.colorScheme.background,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
        ),
        visualTransformation = if(isPasswordField){
            if(isPasswordVisible){
                VisualTransformation.None
            } else {
                PasswordVisualTransformation()
            }
        }else {
            VisualTransformation.None
        },
        trailingIcon = if (isPasswordField) {
            {
                PasswordEyeIcon(isPasswordHidden = isPasswordVisible) {
                    isPasswordVisible = !isPasswordVisible
                }
            }

        } else if(isEditField) {
            trailingIcon
        }
        else {
            null
        }
    )

}
@Composable
fun PasswordEyeIcon(
    modifier: Modifier = Modifier,
    isPasswordHidden: Boolean,
    onPasswordVisibilityToggle: () -> Unit
) {
    val image = if (isPasswordHidden){
        painterResource(id = R.drawable.eye_visible)
    } else {
        painterResource(id = R.drawable.eye_invisible)
    }

    IconButton(
        modifier = modifier,
        onClick = onPasswordVisibilityToggle
    ) {
        Icon(painter = image, contentDescription = null)
    }
}



@Preview(showBackground = true)
@Composable
fun CustomTextFieldPreview(){
    ScheduleAppTheme {
        LoginScreen(rememberNavController())
    }
}

data class LoginUiState (
    val email: String = "",
    val password: String = ""
)