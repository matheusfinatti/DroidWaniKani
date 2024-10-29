package com.mfinatti.wanikanisimple

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.NavDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.mfinatti.wanikanisimple.navigation.TopLevelDestination
import kotlinx.coroutines.CoroutineScope
import androidx.navigation.NavGraph.Companion.findStartDestination
import com.mfinatti.wanikanisimple.home.navigation.navigateToHome
import com.mfinatti.wanikanisimple.levels.navigation.navigateToLevels

@SuppressLint("ComposableNaming")
@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController(),
    coroutineScope: CoroutineScope = rememberCoroutineScope(),
) = remember {
    AppState(navController, coroutineScope)
}

@Stable
class AppState(
    val navController: NavHostController,
    val coroutineScope: CoroutineScope,
) {
    val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentTopLevelDestination: TopLevelDestination?
        @Composable get() = TopLevelDestination.entries.firstOrNull {
            currentDestination?.hasRoute(route = it.route, null) ?: false
        }

    val topLevelDestinations: List<TopLevelDestination> = TopLevelDestination.entries

    fun navigateToTopLevelDestination(topLevelDestination: TopLevelDestination) {
        val topLevelNavOptions = navOptions {
            // Pop up to the start destination of the graph to avoid building up a large stack.
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
            // Avoid multiple copies of the same destination when selecting the same item
            launchSingleTop = true
            // Restore state when selection a previously selected item
            restoreState = true
        }

        when (topLevelDestination) {
            TopLevelDestination.home -> navController.navigateToHome()
            TopLevelDestination.levels -> navController.navigateToLevels()
        }
    }

    @Composable
    fun shouldShowUpNavigation(): Boolean =
        currentDestination != null &&
                currentDestination?.route !in TopLevelDestination.entries.map { it.route }
}
