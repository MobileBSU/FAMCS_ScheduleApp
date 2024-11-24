package org.mobile.scheduleapp.presentation.view.components

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import org.mobile.scheduleapp.presentation.view.theming.Dimens

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    onButtonClicked: () -> Unit,
    @StringRes id: Int
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
        onClick = onButtonClicked
    ) {
        Text(
            stringResource(id = id),
            style = MaterialTheme.typography.bodyMedium,
        )
    }
}