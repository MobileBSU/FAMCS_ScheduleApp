package org.mobile.scheduleapp.presentation.screens.searchScreens.lecturersSearchScreen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import org.koin.androidx.compose.koinViewModel
import org.mobile.scheduleapp.R
import org.mobile.scheduleapp.presentation.screens.searchScreens.Header
import org.mobile.scheduleapp.presentation.view.theming.Dimens
import org.mobile.scheduleapp.screens.searchScreens.CustomSearchBar
import org.mobile.scheduleapp.screens.searchScreens.Divider
import org.mobile.scheduleapp.screens.searchScreens.Dot
import org.mobile.scheduleapp.screens.searchScreens.InfoText
import org.mobile.scheduleapp.presentation.view.theming.NeutralLightMedium
import org.mobile.scheduleapp.presentation.view.theming.NeutralLightWhite


//test version
//data class Lecturer(
//    val fio: String,
//    val faculty: String,
//    val department: String,
//    val imageUrl: String
//){
//    fun doesMatchSearchQuery(query: String): Boolean {
//        val matchingCombinations = listOf(
//            faculty, fio, department
//        )
//        return matchingCombinations.any { it.contains(query, ignoreCase = true) }
//    }
//}
//
////test version
//private val allLecturers = listOf(
//    Lecturer("Казанцева Ольга Геннадьевна","ФПМИ", "МСС", ""),
//    Lecturer("Казанцева Ольга Геннадьевна","ФПМИ", "МСС", "")
//)

@Composable
fun LecturersSearchScreen(
    modifier: Modifier = Modifier
) {
    val viewModel: LecturerSearchViewModel = koinViewModel()
    val state = viewModel.uiState.collectAsState().value

    LecturersSearchScreenLayout(
        modifier = modifier,
        state = state,
        controller = viewModel,
        onLecturerClicked = {})
}

@Composable
fun LecturersSearchScreenLayout(
    modifier: Modifier = Modifier,
    state: LecturerUiState,
    controller: LecturerSearchController,
    onLecturerClicked: (Long) -> Unit
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(8.dp)
    ) {
        Header(stringResource(id = R.string.all_lectures))
        CustomSearchBar(
            searchText = state.searchTest,
            onTextChange = {
                controller.updateSearchText(it)
                controller.getTeacherByName(it)
            })

        Spacer(modifier = Modifier.height(16.dp))

        state.lecturers?.let {
            LecturerList(
                lecturers = it,
                onLecturerClicked = onLecturerClicked
            )
        }

    }
}

@Composable
fun LecturerList(
    modifier: Modifier = Modifier,
    lecturers: List<Lecturer>,
    onLecturerClicked: (Long) -> Unit
) {

    LazyColumn(modifier = Modifier.padding(8.dp)) {
        itemsIndexed(lecturers) { index, lecturer ->
            val shape = when(index) {
                0 -> RoundedCornerShape(topStart = Dimens.SmallCornerShape, topEnd = Dimens.SmallCornerShape)
                lecturers.size - 1 -> RoundedCornerShape(bottomStart = Dimens.SmallCornerShape, bottomEnd = Dimens.SmallCornerShape)
                else -> RoundedCornerShape(0)
            }
            LecturerItem(
                modifier = modifier.background(color = NeutralLightWhite, shape = shape),
                lecturer = lecturer,
                onLecturerClicked = onLecturerClicked
            )
        }
    }
}

@Composable
fun LecturerItem(
    modifier: Modifier = Modifier,
    lecturer: Lecturer,
    onLecturerClicked: (Long) -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clickable { onLecturerClicked(lecturer.id) }
            .padding(horizontal = Dimens.MainHorizontalPadding)
    ) {
        Text(
            text = lecturer.name,
            style = MaterialTheme.typography.labelLarge,
            color = Color.Black,
            modifier = Modifier.padding(top = Dimens.SmallVerticalPadding)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            InfoText(text = stringResource(R.string.famcs))
            Dot()

            Spacer(modifier = modifier.width(10.dp))
            Image(
                painter = rememberAsyncImagePainter(
                    model = lecturer.imageUrl,
                    error = painterResource(R.drawable.ic_launcher_foreground)
                ),
                contentDescription = null,
                modifier = modifier
                    .size(Dimens.SmallIconSize)
                    .clip(RoundedCornerShape(Dimens.SmallCornerShape))
            )
        }
        Spacer(modifier = modifier.height(Dimens.SmallVerticalPadding))

        Divider(color = NeutralLightMedium)
    }
}

@Preview
@Composable
fun Test2(){
    LecturersSearchScreen()
}