package com.mfinatti.wanikanisimple.user

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
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
            Text(text = "Enter your WaniKani API Key to authenticate")
            ApiKeyInput(modifier = Modifier.align(Alignment.CenterHorizontally), apiKey, setApiKey)
            if (loginState is LoginState.Error) {
                ErrorLine(errorMessage = loginState.error.message ?: "Unknown error")
            }
            Button(
                onClick = {
                    viewModel.login(apiKey)
                },
                modifier = Modifier.align(Alignment.CenterHorizontally)
            ) {
                Text(text = "Submit")
            }
        }

    }
}

@Composable
private fun ApiKeyInput(
    modifier: Modifier = Modifier,
    apiKey: String,
    setApiKey: (String) -> Unit,
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