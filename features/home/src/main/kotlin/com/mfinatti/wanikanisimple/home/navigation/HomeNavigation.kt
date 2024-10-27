package com.mfinatti.wanikanisimple.home.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mfinatti.wanikanisimple.Destinations
import com.mfinatti.wanikanisimple.home.ui.HomeScreen
import com.mfinatti.wanikanisimple.home.ui.HomeViewModel

fun NavController.navigateToHome() =
    navigate(route = Destinations.Home)

fun NavGraphBuilder.homeScreen(
) {
    composable(route = Destinations.Home) {
        val viewModel = hiltViewModel<HomeViewModel>()
        HomeScreen(viewModel)
    }
}
