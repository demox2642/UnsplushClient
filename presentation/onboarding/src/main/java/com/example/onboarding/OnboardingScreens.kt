package com.example.onboarding

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.example.onboarding.ui.AuthErrorScreen
import com.example.onboarding.ui.OnboardingMainScreen

sealed class OnboardingScreens(
    val route: String
) {
    object OnboardingMain : OnboardingScreens("onboarding_main")
    object AuthErrorScreen : OnboardingScreens("auth_error")
}

fun NavGraphBuilder.onboardingScreens() {
    composable(OnboardingScreens.OnboardingMain.route) { OnboardingMainScreen() }
    composable(OnboardingScreens.AuthErrorScreen.route) { AuthErrorScreen() }
}
