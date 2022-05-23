
package com.example.onboarding.ui

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.base_ui.theme.AppTheme
import com.example.base_ui.topbar.AppTopBarNav
import com.example.presentation.onboarding.R
import com.example.user.models.Constants.SPLASH_CODE_URL
import com.example.user.models.Constants.SPLASH_KEY
import com.example.user.models.Constants.SPLASH_LOGIN_CALLBACK
import com.example.user.models.Scope
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.android.gms.common.api.ApiException
import kotlinx.coroutines.launch

@OptIn(ExperimentalPagerApi::class, ExperimentalMaterialApi::class)
@Composable
fun OnboardingMainScreen() {

    val viewModel = hiltViewModel< OnboardingMainViewModel>()

    val scopeList = listOf(
        Scope.PUBLIC,
        Scope.READ_COLLECTIONS,
        Scope.PUBLIC,
        Scope.WRITE_COLLECTIONS,
        Scope.READ_USER,
        Scope.WRITE_FOLLOWERS,
        Scope.WRITE_LIKES,
        Scope.WRITE_USER,
        Scope.READ_PHOTOS,
        Scope.WRITE_PHOTOS
    )

    var scopes = StringBuilder()
    for (scope in scopeList) {
        scopes.append(scope.scope).append("+")
    }

    scopes = scopes.deleteCharAt(scopes.length - 1)

    val url =
        "$SPLASH_CODE_URL?client_id=$SPLASH_KEY&redirect_uri=$SPLASH_LOGIN_CALLBACK&response_type=code&scope=$scopes"

    val launcher = rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { task ->
        task
            .run {
                try {
                    val gsa = task.resultCode
                    Log.e("Screen launch", "result = $gsa")
                } catch (e: ApiException) {
                    Log.e("Screen launch", e.toString())
                }
            }
    }

    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

    val scope = rememberCoroutineScope()
    val onBoardItem by viewModel.onBoardItem.collectAsState()
    val pagerState = rememberPagerState(
        pageCount = onBoardItem.size,
        initialOffscreenLimit = 2,
        initialPage = 0,
        infiniteLoop = false
    )

    val next: () -> Unit = {
        scope.launch {
            pagerState.animateScrollToPage(
                page = pagerState.currentPage + 1,
                animationSpec = tween(600)
            )
        }
    }

    val back: () -> Unit = {
        scope.launch {
            pagerState.animateScrollToPage(
                page = pagerState.currentPage - 1,
                animationSpec = tween(600)
            )
        }
    }

    Scaffold(
        topBar = {

            when (pagerState.currentPage) {
                0 -> {
                    AppTopBarNav(
                        bottomText = null,
                        back = null,
                        next = next

                    )
                }
                (onBoardItem.size - 1) -> {
                    AppTopBarNav(
                        bottomText = null,
                        back = back,
                        next = {
                            viewModel.saveOnboardingState()
                            launcher.launch(
                                intent
                            )
                        }
                    )
                }
                else -> {
                    AppTopBarNav(
                        bottomText = null,
                        back = back,
                        next = next
                    )
                }
            }
        },
        backgroundColor = AppTheme.colors.systemBackgroundPrimary
    ) {

        ConstraintLayout() {
            val (image, pager) = createRefs()

            Image(
                painter = painterResource(id = R.drawable.onboarding_backgraund),
                contentDescription = "background",
                modifier = Modifier
                    .constrainAs(image) {
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    }
                    .fillMaxWidth()
            )

            HorizontalPager(
                modifier = Modifier
                    .fillMaxSize()
                    .constrainAs(pager) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                    },
                state = pagerState
            ) { page ->
                Column(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Image(
                        painter = painterResource(id = onBoardItem[page].image),
                        contentDescription = "OnBoardImage",
                        modifier = Modifier
                            .size(350.dp)
                    )

                    Text(
                        text = stringResource(id = onBoardItem[page].desc),
                        modifier = Modifier
                            .padding(top = 50.dp, start = 16.dp, end = 16.dp)
                            .align(alignment = Alignment.CenterHorizontally),
                        color = AppTheme.colors.systemTextPrimary,
                        style = AppTheme.typography.h3
                    )
                }
            }
        }
    }
}
