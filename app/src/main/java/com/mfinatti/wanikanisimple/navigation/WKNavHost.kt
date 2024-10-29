package com.mfinatti.wanikanisimple.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.mfinatti.wanikanisimple.AppState
import com.mfinatti.wanikanisimple.Destinations
import com.mfinatti.wanikanisimple.home.navigation.homeScreen
import com.mfinatti.wanikanisimple.home.navigation.navigateToHome
import com.mfinatti.wanikanisimple.levels.navigation.levelScreen
import com.mfinatti.wanikanisimple.levels.navigation.levelsScreen
import com.mfinatti.wanikanisimple.levels.navigation.navigateToLevel
import com.mfinatti.wanikanisimple.login.navigation.loginScreen
import com.mfinatti.wanikanisimple.login.navigation.navigateToLogin
import com.mfinatti.wanikanisimple.navigation.TopLevelDestination.home
import com.mfinatti.wanikanisimple.splash.navigation.splashScreen

@Composable
fun WKNavHost(
    appState: AppState,
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Destinations.Splash,
        modifier = Modifier,
    ) {
        splashScreen(
            onLoggedIn = { appState.navigateToTopLevelDestination(home) },
            onLoggedOut = navController::navigateToLogin,
        )
        loginScreen(onLoginSuccess = { appState.navigateToTopLevelDestination(home) })
        homeScreen(modifier)
        levelsScreen(modifier, onLevelSelected = navController::navigateToLevel)
        levelScreen(modifier)
    }
}
