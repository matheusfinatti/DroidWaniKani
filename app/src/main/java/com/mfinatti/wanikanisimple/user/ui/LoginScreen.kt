package com.mfinatti.wanikanisimple.user.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun LoginScreen(
    modifier: Modifier,
    viewModel: UserViewModel,
) {
    val loginState = viewModel.loginState.collectAsState().value

    val (apiKey, setApiKey) = remember {
        mutableStateOf("")
    }

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            when (loginState) {
                is LoginState.Init -> LoginInput(
                    errorMessage = null,
                    apiKey,
                    setApiKey,
                    submitOnClick = {
                        viewModel.login(apiKey)
                    },
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                is LoginState.Loading -> CircularProgressIndicator()
                is LoginState.Error -> LoginInput(
                    errorMessage = loginState.error.message ?: "Unknown error",
                    apiKey = apiKey,
                    setApiKey = setApiKey,
                    submitOnClick = {
                        viewModel.login(apiKey)
                    })

                is LoginState.Success ->
                    Text(
                        text = "Welcome, ${loginState.user.username}",
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
            }
        }

    }
}

@Composable
private fun LoginInput(
    errorMessage: String?,
    apiKey: String,
    setApiKey: (String) -> Unit,
    submitOnClick: () -> Unit,
    modifier: Modifier = Modifier,
) {

    Text(text = "Enter your WaniKani API Key to authenticate", modifier = modifier)
    ApiKeyInput(apiKey, setApiKey, modifier = modifier)
    if (!errorMessage.isNullOrEmpty()) {
        ErrorLine(errorMessage = errorMessage)
    }
    Button(
        onClick = submitOnClick,
        modifier = modifier,
    ) {
        Text(text = "Submit")
    }
}

@Composable
private fun ApiKeyInput(
    apiKey: String,
    setApiKey: (String) -> Unit,
    modifier: Modifier = Modifier,
) {

    OutlinedTextField(
        modifier = modifier,
        value = apiKey,
        label = { Text("API Key") },
        onValueChange = { value ->
            setApiKey(value)
        })
}

@Composable
private fun ErrorLine(
    errorMessage: String,
    modifier: Modifier = Modifier,
) {
    Text(modifier = modifier, text = errorMessage, color = Color.Red)
}