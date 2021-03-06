package com.example.home

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.* // ktlint-disable no-wildcard-imports
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.* // ktlint-disable no-wildcard-imports
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.example.HomeScreens
import com.example.base_ui.errorlisiner.ErrorListener
import com.example.base_ui.photo.BasePhoto
import com.example.base_ui.photo.BasePhotoScreen
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
    // Log.e("ListContent", "Start list = ${list.itemSnapshotList}")
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
                    val photoIm = BasePhoto(
                        id = list[index]!!.id,
                        likes = list[index]!!.likes,
                        urls_regular = list[index]!!.urls_regular,
                        user_name = list[index]!!.user_name,
                        user_fio = list[index]!!.user_fio,
                        user_img = list[index]!!.user_img,
                        likedByUser = list[index]!!.likedByUser,
                    )
                    // UnsplashItem(unsplashImage = list[index]!!, photoId)
                    BasePhotoScreen(unsplashImage = photoIm, Modifier.clickable { photoId(list[index]!!.id) })
                }
            } else {

                if (list[index] != null) {
                    val photoIm = BasePhoto(
                        id = list[index]!!.id,
                        likes = list[index]!!.likes,
                        urls_regular = list[index]!!.urls_regular,
                        user_name = list[index]!!.user_name,
                        user_fio = list[index]!!.user_fio,
                        user_img = list[index]!!.user_img,
                        likedByUser = list[index]!!.likedByUser,
                    )
                    item(span = { GridItemSpan(1) }) {
                        // UnsplashItem(unsplashImage = list[index]!!, photoId)
                        BasePhotoScreen(unsplashImage = photoIm, Modifier.clickable { photoId(list[index]!!.id) })
                    }
                }
            }
        }
    }
}
//
// @ExperimentalCoilApi
// @Composable
// fun UnsplashItem(unsplashImage: HomePhoto, photoId: (String) -> Unit) {
//    Log.e("HomeScreen", "HomePhoto = $unsplashImage")
//    val painter = rememberImagePainter(data = unsplashImage.urls_regular) {
//        crossfade(durationMillis = 100)
//        error(R.drawable.ic_placeholder)
//        placeholder(R.drawable.ic_placeholder)
//    }
//    val context = LocalContext.current
//    Box(
//        modifier = Modifier
//            .clickable {
//                photoId(unsplashImage.id)
//            }
//            .height(300.dp)
//            .fillMaxWidth(),
//        contentAlignment = Alignment.BottomCenter
//    ) {
//        Image(
//            modifier = Modifier.fillMaxSize(),
//            painter = painter,
//            contentDescription = "Unsplash Image",
//            contentScale = ContentScale.Crop
//        )
//        Surface(
//            modifier = Modifier
//                .height(40.dp)
//                .fillMaxWidth()
//                .alpha(ContentAlpha.medium),
//            color = Color.Black
//        ) {}
//        Row(
//            modifier = Modifier
//                .height(40.dp)
//                .fillMaxWidth()
//                .padding(horizontal = 6.dp),
//            verticalAlignment = Alignment.CenterVertically,
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Image(
//                painter = rememberImagePainter(data = unsplashImage.user_img),
//                contentDescription = "userDomain image",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .size(25.dp)
//                    .clip(CircleShape)
//            )
//            Spacer(modifier = Modifier.width(15.dp))
//            Text(
//                text = buildAnnotatedString {
//                    //  append("Photo by ")
//                    withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
//                        append(unsplashImage.user_name?.take(9) ?: "")
//                    }
//                    // append(" on Unsplash")
//                },
//                color = Color.White,
//                fontSize = MaterialTheme.typography.caption.fontSize,
//                maxLines = 1,
//                overflow = TextOverflow.Ellipsis
//            )
//            LikeCounter(
//                modifier = Modifier.weight(3f),
//                painter = painterResource(id = R.drawable.ic_heart),
//                likes = "${unsplashImage.likes}",
//                userLikeIt = unsplashImage.likedByUser!!
//            )
//        }
//    }
// }
//
// @Composable
// fun LikeCounter(
//    modifier: Modifier,
//    painter: Painter,
//    likes: String,
//    userLikeIt: Boolean
// ) {
//    Row(
//        modifier = modifier.fillMaxSize(),
//        verticalAlignment = Alignment.CenterVertically,
//        horizontalArrangement = Arrangement.End
//    ) {
//        Icon(
//            painter = painter,
//            contentDescription = "Heart Icon",
//            tint = if (userLikeIt) {
//                Color.Red
//            } else {
//                Color.Gray
//            }
//        )
//        Divider(modifier = Modifier.width(6.dp))
//        Text(
//            text = likes,
//            color = Color.White,
//            fontSize = MaterialTheme.typography.subtitle1.fontSize,
//            fontWeight = FontWeight.Bold,
//            maxLines = 1,
//            overflow = TextOverflow.Ellipsis
//        )
//    }
// }
