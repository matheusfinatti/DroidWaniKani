package com.mfinatti.wanikanisimple.levels.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.mfinatti.wanikanisimple.Destinations
import com.mfinatti.wanikanisimple.levels.ui.LevelScreen
import com.mfinatti.wanikanisimple.levels.ui.LevelsScreen

fun NavController.navigateToLevels(navOptions: NavOptions) =
    navigate(route = Destinations.Levels, navOptions)

fun NavGraphBuilder.levelsScreen(
    modifier: Modifier = Modifier,
    onLevelSelected: (Int) -> Unit = {}
) {
    composable(route = Destinations.Levels) {
        LevelsScreen(modifier) { level ->
            onLevelSelected(level)
        }
    }
}

fun NavController.navigateToLevel(level: Int) =
    navigate(route = "level/${level}")

fun NavGraphBuilder.levelScreen(
    modifier: Modifier = Modifier,
) {
    composable(route = "level/{level}") { navBackStackEntry ->
        val level = navBackStackEntry.arguments?.getString("level")?.toIntOrNull() ?: error("Invalid level")
        LevelScreen(level = level, modifier = modifier)
    }
}
