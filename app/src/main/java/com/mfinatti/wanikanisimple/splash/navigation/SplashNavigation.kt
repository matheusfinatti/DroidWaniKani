package com.mfinatti.wanikanisimple.splash.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mfinatti.wanikanisimple.Destinations
import com.mfinatti.wanikanisimple.splash.ui.SplashScreen

fun NavGraphBuilder.splashScreen(
    onLoggedIn: () -> Unit,
    onLoggedOut: () -> Unit,
) {
    composable(route = Destinations.Splash) {
        SplashScreen(
            onNavToLogin = onLoggedOut,
            onNavToHome = onLoggedIn,
        )
    }
}
