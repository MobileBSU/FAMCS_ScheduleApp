package org.mobile.scheduleapp.screens.searchScreens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.view.theming.Dimens.SearchBarIconSize
import org.mobile.scheduleapp.presentation.view.theming.NeutralLightMedium

@Composable
fun CustomSearchBar(searchText: String, onTextChange: (String) -> Unit) {
    TextField(
        value = searchText,
        onValueChange = onTextChange,
        modifier = Modifier
            .fillMaxWidth()
            .clip(MaterialTheme.shapes.large)
            .background(NeutralLightMedium),
        leadingIcon = {
            Icon(
                painter = painterResource(id = R.drawable.search_bar_icon),
                contentDescription = stringResource(R.string.search_icon),
                modifier = Modifier.size(SearchBarIconSize)
            )
        },
        placeholder = {
            Text(
                text = stringResource(R.string.search),
                style = MaterialTheme.typography.labelMedium
            )
        },
        textStyle = MaterialTheme.typography.labelMedium,
        colors = TextFieldDefaults.colors(
            Color.Black
        )
    )
}
