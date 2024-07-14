package com.mfinatti.wanikanisimple.splash.ui

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.mfinatti.wanikanisimple.Consts
import com.mfinatti.wanikanisimple.user.domain.User

@Composable
fun SplashScreen(
    viewModel: SplashViewModel,
    onNavToLogin: () -> Unit,
    onNavToHome: (User) -> Unit,
    modifier: Modifier = Modifier,
) {
    val loadingState by viewModel.loadingState.collectAsState()
    Log.d(Consts.TAG, "loadingState $loadingState")

    LaunchedEffect(loadingState) {
        when (val state = loadingState) {
            LoadingState.NotLoggedIn -> onNavToLogin()
            is LoadingState.Loaded -> onNavToHome(state.user)
            else -> Unit
        }
    }

    Box(modifier = modifier.fillMaxSize()) {
        when (val state = loadingState) {
            is LoadingState.Error -> SplashError(
                errorMessage = state.error.message ?: "Unknown Error"
            )

            LoadingState.Loading -> SplashLoading()
            else -> Unit
        }
    }
}

@Composable
private fun SplashError(
    errorMessage: String,
    modifier: Modifier = Modifier,
) {
    Text(text = errorMessage, color = Color.Red, modifier = modifier)
}

@Composable
private fun SplashLoading(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(modifier = modifier)
}
