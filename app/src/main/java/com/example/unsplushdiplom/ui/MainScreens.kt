package com.example.unsplushdiplom.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.collections.CollectionsMainScreen
import com.example.home.HomeMainScreen
import com.example.profile.ProfileMainScreen
import com.example.unsplushdiplom.R

sealed class MainScreen(
    val route: String,
    @DrawableRes val iconId: Int,
    @StringRes val resourceId: Int
) {
    object Home : MainScreen("home", R.drawable.ic_menu_home, R.string.menu_home)
    object Collections : MainScreen("collections", R.drawable.ic_menu_collections, R.string.menu_collections)
    object Profile : MainScreen("profile", R.drawable.ic_menu_profile, R.string.menu_profile)
}

fun NavGraphBuilder.mainScreens() {
    composable(MainScreen.Home.route) { HomeMainScreen() }
    composable(MainScreen.Collections.route) { CollectionsMainScreen() }
    composable(MainScreen.Profile.route) { ProfileMainScreen() }
}
