package org.mobile.scheduleapp.screens.searchScreens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import org.mobile.scheduleapp.R

@Composable
fun Dot() {
    Image(
        painter = painterResource(id = R.drawable.dot),
        contentDescription = "dot",
        modifier = Modifier.size(2.dp)
    )
}