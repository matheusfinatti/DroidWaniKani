package com.mfinatti.wanikanisimple.login.navigation

import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.mfinatti.wanikanisimple.Destinations
import com.mfinatti.wanikanisimple.login.ui.LoginScreen
import com.mfinatti.wanikanisimple.login.ui.LoginViewModel

fun NavController.navigateToLogin() =
    navigate(route = Destinations.Login)

fun NavGraphBuilder.loginScreen(
    onLoginSuccess: () -> Unit,
) {
    composable(route = Destinations.Login) {
        val viewModel = hiltViewModel<LoginViewModel>()
        LoginScreen(viewModel, onLoginSuccess)
    }
}
