package com.example.unsplushdiplom.ui

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.base_ui.theme.AppTheme
import com.example.homeScreens
import com.example.onboarding.OnboardingScreens
import com.example.onboarding.onboardingScreens

@Composable
fun UnsplashMainScreen() {

    val viewModel = hiltViewModel< UnsplashMainViewModel>()

    val navController = rememberNavController()
    val onboardingState = viewModel.onboardingState.collectAsState()
    val haveToken = viewModel.haveToken.collectAsState()
    Log.e("UnsplashMainScreen", "onboardingState = $onboardingState  haveToken = $haveToken")

    Scaffold(
        bottomBar = { BottomNav(navController) }
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            NavHost(
                navController = navController,
                startDestination =

                if (onboardingState.value && haveToken.value) {
                    MainScreen.Home.route
                } else {

                    if (haveToken.value) {
                        Log.e("UnsplashMainScreen", "true")
                        OnboardingScreens.AuthErrorScreen.route
                    } else {
                        Log.e("UnsplashMainScreen", "false")
                        OnboardingScreens.OnboardingMain.route
                    }
                }

            ) {
                mainScreens(navController)
                homeScreens(navController)
                onboardingScreens()
            }
        }
    }
}

val showNavBar = listOf(
    MainScreen.Home,
    MainScreen.Collections,
    MainScreen.Profile
).map { it.route }

@Composable
fun BottomNav(navController: NavHostController) {
    val items = listOf(
        MainScreen.Home,
        MainScreen.Collections,
        MainScreen.Profile
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    if (currentDestination?.route in showNavBar) {
        BottomNavigation(
            backgroundColor = AppTheme.colors.systemBackgroundPrimary,
            modifier = Modifier
                .defaultMinSize(minWidth = 1.dp),
            elevation = 0.dp
        ) {

            items.forEach { screen ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painterResource(screen.iconId),
                            contentDescription = null
                        )
                    },
                    label = {
                        Text(stringResource(screen.resourceId), maxLines = 1, softWrap = true)
                    },
                    alwaysShowLabel = false,
                    selectedContentColor = AppTheme.colors.systemButtonPrimaryColor,
                    unselectedContentColor = AppTheme.colors.systemGraphSecondary,
                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,

                    onClick = {
                        navController.navigate(screen.route) {
                            // Pop up to the start destination of the graph to
                            // avoid building up a large stack of destinations
                            // on the back stack as users select items
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }
}
