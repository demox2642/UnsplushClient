package com.example

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.detailphoto.DetailPhotoScreen

sealed class HomeScreens(
    val route: String
) {
    object DetailPhoto : HomeScreens("detail_photo")
}

fun NavGraphBuilder.homeScreens(navController: NavHostController) {
    composable(
        HomeScreens.DetailPhoto.route + "/{photoId}",
        arguments = listOf(navArgument(name = "photoId") { NavType.StringType })
    ) {
            backStackEntry ->
        val arguments =
            (backStackEntry.arguments?.getString("photoId") ?: return@composable)
        DetailPhotoScreen(arguments, navController)
    }
}
