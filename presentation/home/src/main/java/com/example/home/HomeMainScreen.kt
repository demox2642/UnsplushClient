package com.example.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.HomeScreens
import com.example.base_ui.errorlisiner.ErrorListener
import com.example.base_ui.topbar.TopBarSearch
import com.example.home.models.HomePhoto
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeMainScreen(navController: NavHostController) {

    val viewModel = hiltViewModel< HomeMainScreenViewModel>()
    val searchedImages = viewModel.photoList.collectAsLazyPagingItems()
    val swipeRefreshState by viewModel.refreshState.collectAsState()
    val errorMessage by viewModel.errorMessage.collectAsState()

    val openErrorDialog by viewModel.errorMessageState.collectAsState()
    val searchText by viewModel.searchText.collectAsState()

    viewModel.changeRefreshStatus(searchedImages.loadState.prepend.endOfPaginationReached.not())
    if (searchedImages.loadState.mediator != null) {

        viewModel.changeRefreshStatus(searchedImages.loadState.mediator!!.refresh.equals(LoadState.Loading))
        searchedImages.loadState.mediator!!.refresh.toString()
        viewModel.postError(searchedImages.loadState.mediator!!.refresh)
    }
    val defValueText = stringResource(id = R.string.search_text)

    Scaffold(
        topBar = { TopBarSearch(defValueText = defValueText, text = searchText, viewModel::search) },

        content = {
            SwipeRefresh(

                state = rememberSwipeRefreshState(isRefreshing = swipeRefreshState), // remeberSwipeRefreshState(swipeRefreshState),
                onRefresh = { viewModel.getPhotosList() }

            ) {
                ListContent(list = searchedImages) {
                    navController.navigate(HomeScreens.DetailPhoto.route + "/$it")
                }
                if (openErrorDialog) {
                    Column() {
                        ErrorListener(
                            error = errorMessage!!,
                            closeDialog = viewModel::closeErrorDialog
                        )
                    }
                }

//
//                    Coumn(
//                        modifier = Modifier.fillMaxHeight(),
//                        verticalArrangement = Arrangement.Center
//                    ) {
//                        CustomErrorDialog(
//
//                            title = "Uncknow Error",
//                            message = errorMessage,
//                            dismissButtonText = "Exit",
//                            closeDialog = viewModel::closeErrorDialog
//                        )
//                    }
//
            }
        }
    )
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalCoilApi
@Composable
fun ListContent(list: LazyPagingItems<HomePhoto>, photoId: (String) -> Unit) {

//    LazyColumn(
//        modifier = Modifier.fillMaxSize(),
//        contentPadding = PaddingValues(all = 12.dp),
//        verticalArrangement = Arrangement.spacedBy(12.dp)
//    ) {
//        items(
//            items = items,
//            key = { unsplashImage ->
//                unsplashImage.id
//            }
//        ) { unsplashImage ->
//            unsplashImage?.let { UnsplashItem(unsplashImage = it, photoId) }
//        }
//    }

//    val configuration = LocalConfiguration.current
//
//    val dimensions = if (configuration.screenWidthDp <= 400) 2 else 3
//
//    LazyVerticalGrid(
//        columns = GridCells.Fixed(dimensions),
//        modifier = Modifier.padding(4.dp)
//    ) {
//        items(list.itemCount) { index ->
//            list[index]?.let {
//                UnsplashItem(unsplashImage = it, photoId)
//            }
//            //     unsplashImage?.let {}
//        }
//    }

    LazyVerticalGrid(
        columns = object : GridCells {

            override fun Density.calculateCrossAxisCellSizes(
                availableSize: Int,
                spacing: Int
            ): List<Int> {
                val firstColumn = (availableSize - spacing) * 1 / 2
                val secondColumn = availableSize - spacing - firstColumn
                return listOf(firstColumn, secondColumn)
            }
        },
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(10.dp)
    ) {
        list.itemSnapshotList.forEachIndexed { index, photo ->
            if (index % 3 == 0) {
                item(span = { GridItemSpan(maxLineSpan) }) {
                    UnsplashItem(unsplashImage = list[index]!!, photoId)
                }
            } else {
                item(span = { GridItemSpan(1) }) {
                    UnsplashItem(unsplashImage = list[index]!!, photoId)
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun UnsplashItem(unsplashImage: HomePhoto, photoId: (String) -> Unit) {
    Log.e("HomeScreen", "HomePhoto = $unsplashImage")
    val painter = rememberImagePainter(data = unsplashImage.urls_regular) {
        crossfade(durationMillis = 100)
        error(R.drawable.ic_placeholder)
        placeholder(R.drawable.ic_placeholder)
    }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clickable {
                photoId(unsplashImage.id)
            }
            .height(300.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painter,
            contentDescription = "Unsplash Image",
            contentScale = ContentScale.Crop
        )
        Surface(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .alpha(ContentAlpha.medium),
            color = Color.Black
        ) {}
        Row(
            modifier = Modifier
                .height(40.dp)
                .fillMaxWidth()
                .padding(horizontal = 6.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = rememberImagePainter(data = unsplashImage.user_img),
                contentDescription = "userDomain image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(25.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.width(15.dp))
            Text(
                text = buildAnnotatedString {
                    //  append("Photo by ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                        append(unsplashImage.user_name?.take(9) ?: "")
                    }
                    // append(" on Unsplash")
                },
                color = Color.White,
                fontSize = MaterialTheme.typography.caption.fontSize,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            LikeCounter(
                modifier = Modifier.weight(3f),
                painter = painterResource(id = R.drawable.ic_heart),
                likes = "${unsplashImage.likes}",
                userLikeIt = unsplashImage.likedByUser!!
            )
        }
    }
}

@Composable
fun LikeCounter(
    modifier: Modifier,
    painter: Painter,
    likes: String,
    userLikeIt: Boolean
) {
    Row(
        modifier = modifier.fillMaxSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.End
    ) {
        Icon(
            painter = painter,
            contentDescription = "Heart Icon",
            tint = if (userLikeIt) {
                Color.Red
            } else {
                Color.Gray
            }
        )
        Divider(modifier = Modifier.width(6.dp))
        Text(
            text = likes,
            color = Color.White,
            fontSize = MaterialTheme.typography.subtitle1.fontSize,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

// @ExperimentalCoilApi
// @Composable
// @Preview
// fun UnsplashImagePreview() {
//    UnsplashItem(
//        unsplashImage = UnsplashImage(
//            id = "1",
//            urlsDomain = UrlsDomain(regular = ""),
//            likes = 100,
//            userDomain = UserDomain(username = "Stevdza-San", userLinks = UserLinks(html = ""))
//        )
//    )
// }
