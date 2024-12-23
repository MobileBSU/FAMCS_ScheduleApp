package org.mobile.scheduleapp.presentation.view.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import org.mobile.scheduleapp.presentation.view.theming.Dimens
import org.mobile.scheduleapp.presentation.view.theming.NeutralLightDark

@Composable
fun CodeInputTextField(
    modifier: Modifier = Modifier,
    onCodeChange: (String) -> Unit,
    value: String,
) {
    var isFocused by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onCodeChange,
        modifier = modifier
            .size(Dimens.CodeBoxSize)
            .onFocusChanged { focusState ->
                isFocused = focusState.isFocused
            }
            .clip(
                shape = RoundedCornerShape(Dimens.CodeInputTextFieldCorner)
            )
            .border(
                width = 2.dp,
                color = if (isFocused) MaterialTheme.colorScheme.primary else NeutralLightDark,
                shape = RoundedCornerShape(Dimens.CodeInputTextFieldCorner)
            ),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
    )
}
