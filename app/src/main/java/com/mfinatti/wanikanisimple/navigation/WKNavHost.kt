package com.mfinatti.wanikanisimple.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mfinatti.wanikanisimple.Destinations
import com.mfinatti.wanikanisimple.home.navigation.homeScreen
import com.mfinatti.wanikanisimple.home.navigation.navigateToHome
import com.mfinatti.wanikanisimple.login.navigation.loginScreen
import com.mfinatti.wanikanisimple.login.navigation.navigateToLogin
import com.mfinatti.wanikanisimple.splash.navigation.splashScreen

@Composable
fun WKNavHost(
    modifier: Modifier = Modifier,
) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Destinations.Splash,
        modifier = modifier,
    ) {
        splashScreen(
            onLoggedIn = navController::navigateToHome,
            onLoggedOut = navController::navigateToLogin,
        )
        loginScreen(onLoginSuccess = navController::navigateToHome)
        homeScreen()
    }
}