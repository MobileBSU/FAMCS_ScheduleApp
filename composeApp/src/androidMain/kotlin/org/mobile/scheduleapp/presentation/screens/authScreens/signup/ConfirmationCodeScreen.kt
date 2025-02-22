package org.mobile.scheduleapp.presentation.screens.authScreens.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.view.components.CodeInputTextField
import org.mobile.scheduleapp.presentation.view.components.CustomButton
import org.mobile.scheduleapp.presentation.view.theming.Dimens
import org.mobile.scheduleapp.presentation.view.theming.ScheduleAppTheme

@Composable
fun ConfirmationCodeScreen() {

}

@Composable
fun ConfirmationScreenLayout(
    modifier : Modifier = Modifier,
    email: String,
    onResendButtonClicked: () -> Unit,
    onContinueButtonClicked: (String) -> Unit,
    onCodeChange: (String) -> Unit
) {

    val code by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = Dimens.MainHorizontalPadding)
            .background(MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = modifier,
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(Dimens.SmallVerticalPadding)
        ) {
            Text(
                text = stringResource(
                    id = R.string.enter_confimation_code
                ),
                style = MaterialTheme.typography.displayMedium,
                color = MaterialTheme.colorScheme.onSecondary,
                maxLines = 1
            )
            
            Text(
                text = stringResource(
                    id = R.string.confirmation_code_digital) +"\n"+ email,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurface
            )
            Spacer(modifier = modifier.height(Dimens.MainVerticalPadding))
            Row(
                modifier = modifier
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(space = Dimens.SmallVerticalPadding, alignment = Alignment.CenterHorizontally)
            ){
                CodeInputTextField(onCodeChange = {}, value = "")
                CodeInputTextField(onCodeChange = {}, value = "")
                CodeInputTextField(onCodeChange = {}, value = "")
                CodeInputTextField(onCodeChange = {}, value = "")

            }
        }

        Spacer(modifier = modifier.height(Dimens.MainVerticalPadding))

        CustomButton(
            onButtonClicked = {
                              onContinueButtonClicked(code)
            },
            id = R.string.continue_string)
    }
}


@Preview(showBackground = true)
@Composable
fun Prev() {
    ScheduleAppTheme {
        ConfirmationScreenLayout(
            email = "your@emal.com",
            onResendButtonClicked = { /*TODO*/ },
            onContinueButtonClicked = {},
            onCodeChange = {}
        )
    }

}