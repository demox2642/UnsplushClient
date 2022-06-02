package com.example.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
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
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.HomeScreens
import com.example.base_ui.dialogs.CustomErrorDialog
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
                ListContent(items = searchedImages) {
                    navController.navigate(HomeScreens.DetailPhoto.route + "/$it")
                }

                if (openErrorDialog) {
                    Column(
                        modifier = Modifier.fillMaxHeight(),
                        verticalArrangement = Arrangement.Center
                    ) {
                        CustomErrorDialog(

                            title = "Uncknow Error",
                            message = errorMessage,
                            dismissButtonText = "Exit",
                            closeDialog = viewModel::closeErrorDialog
                        )
                    }
                }

                Log.e("Error", "errorMessage = ${errorMessage.isNotEmpty()}")
            }
        }
    )
}

@ExperimentalCoilApi
@Composable
fun ListContent(items: LazyPagingItems<HomePhoto>, photoId: (String) -> Unit) {

    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(all = 12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = items,
            key = { unsplashImage ->
                unsplashImage.id
            }
        ) { unsplashImage ->
            unsplashImage?.let { UnsplashItem(unsplashImage = it, photoId) }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun UnsplashItem(unsplashImage: HomePhoto, photoId: (String) -> Unit) {
    val painter = rememberImagePainter(data = unsplashImage.urls_regular) {
        crossfade(durationMillis = 1000)
        error(R.drawable.ic_placeholder)
        placeholder(R.drawable.ic_placeholder)
    }
    val context = LocalContext.current
    Box(
        modifier = Modifier
            .clickable {
                photoId(unsplashImage.id)
//                val browserIntent = Intent(
//                    Intent.ACTION_VIEW,
//                    Uri.parse("https://unsplash.com/@${unsplashImage.user_name}?utm_source=DemoApp&utm_medium=referral")
//                )
//                ContextCompat.startActivity(context, browserIntent, null)
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
            Text(
                text = buildAnnotatedString {
                    append("Photo by ")
                    withStyle(style = SpanStyle(fontWeight = FontWeight.Black)) {
                        append(unsplashImage.user_name ?: "")
                    }
                    append(" on Unsplash")
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
                Color.DarkGray
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
//            urls = Urls(regular = ""),
//            likes = 100,
//            user = User(username = "Stevdza-San", userLinks = UserLinks(html = ""))
//        )
//    )
// }
