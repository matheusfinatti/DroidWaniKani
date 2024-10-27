package com.mfinatti.wanikanisimple.home.navigation

import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mfinatti.wanikanisimple.Destinations
import com.mfinatti.wanikanisimple.home.ui.HomeScreen

fun NavController.navigateToHome() =
    navigate(route = Destinations.Home)

fun NavGraphBuilder.homeScreen(
    modifier: Modifier = Modifier,
) {
    composable(route = Destinations.Home) {
        HomeScreen(modifier)
    }
}
