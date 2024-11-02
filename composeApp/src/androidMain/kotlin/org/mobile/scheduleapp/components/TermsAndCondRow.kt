package org.mobile.scheduleapp.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.theming.Dimens
import org.mobile.scheduleapp.theming.ScheduleAppTheme

@Composable
fun TermsAndCondRow(
    modifier: Modifier = Modifier,
    onTermsClicked: () -> Unit,
    onPrivacyClicked: () -> Unit,
    isToggleCheckBox: Boolean,
    onTermsAcceptedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = isToggleCheckBox,
            onCheckedChange = {
                onTermsAcceptedChange(it)
            }
        )

        val annotatedString = buildAnnotatedString {
            append(stringResource(id = R.string.i_have_read))

            pushStringAnnotation(
                tag = stringResource(id = R.string.terms),
                annotation = stringResource(id = R.string.terms_small)
            )

            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append(stringResource(id = R.string.terms_and_privacy))
            }
            pop()

            append(stringResource(id = R.string.and_the))

            pushStringAnnotation(
                tag = stringResource(id = R.string.PRIVACY),
                annotation = stringResource(id = R.string.privacy)
            )
            withStyle(
                style = SpanStyle(
                    color = MaterialTheme.colorScheme.primary
                )
            ) {
                append(
                    stringResource(id = R.string.privacy_policy)
                )
            }
            pop()

            append(stringResource(id = R.string.dot))
        }

        val termsString = stringResource(id = R.string.terms)
        val privacyString = stringResource(id = R.string.PRIVACY)

        ClickableText(
            text = annotatedString,
            style = MaterialTheme.typography.bodyMedium,
            onClick = { offset ->
                annotatedString.getStringAnnotations(
                    tag = termsString,
                    start = offset,
                    end = offset
                )
                    .firstOrNull()?.let {
                        onTermsClicked()
                    }

                annotatedString.getStringAnnotations(
                    tag = privacyString,
                    start = offset,
                    end = offset
                )
                    .firstOrNull()?.let {
                        onPrivacyClicked()
                    }
            },
            modifier = Modifier.padding(Dimens.LargeSpaceBetween)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun TermsAndCondRowPreview() {
    ScheduleAppTheme {
        TermsAndCondRow(
            onTermsClicked = { /*TODO*/ },
            onPrivacyClicked = { /*TODO*/ },
            isToggleCheckBox = false,
            onTermsAcceptedChange = {}
        )
    }
}