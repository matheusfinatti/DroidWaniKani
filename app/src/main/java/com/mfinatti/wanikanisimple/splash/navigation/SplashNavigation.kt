package com.mfinatti.wanikanisimple.splash.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mfinatti.wanikanisimple.Destinations
import com.mfinatti.wanikanisimple.splash.ui.SplashScreen
import com.mfinatti.wanikanisimple.splash.ui.SplashViewModel

fun NavController.navigateToSplash() =
    navigate(route = Destinations.Splash)

fun NavGraphBuilder.splashScreen(
    onLoggedIn: () -> Unit,
    onLoggedOut: () -> Unit,
) {
    composable(route = Destinations.Splash) {
        val viewModel = hiltViewModel<SplashViewModel>()
        SplashScreen(
            viewModel = viewModel,
            onNavToLogin = onLoggedOut,
            onNavToHome = onLoggedIn,
        )
    }
}
