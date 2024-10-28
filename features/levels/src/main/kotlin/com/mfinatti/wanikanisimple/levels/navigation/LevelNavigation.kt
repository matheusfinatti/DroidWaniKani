package com.mfinatti.wanikanisimple.levels.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mfinatti.wanikanisimple.Destinations
import com.mfinatti.wanikanisimple.levels.ui.LevelsScreen

fun NavController.navigateToLevels() =
    navigate(route = Destinations.Levels)

fun NavGraphBuilder.levelsScreen(
    modifier: Modifier = Modifier,
) {
    composable(route = Destinations.Levels) {
        LevelsScreen(modifier)
    }
}
