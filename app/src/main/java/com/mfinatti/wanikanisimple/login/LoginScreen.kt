package com.mfinatti.wanikanisimple.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun LoginScreen(
    modifier: Modifier,
) {
    // TODO: ViewModel here

    Box(modifier = modifier.fillMaxSize()) {
        Column(modifier = Modifier.align(Alignment.Center)) {
            Text(text = "Enter your WaniKani API Key to authenticate")
            ApiKeyInput(modifier = Modifier.align(Alignment.CenterHorizontally))
        }

    }
}

@Composable
private fun ApiKeyInput(
    modifier: Modifier = Modifier,
) {
    var apiKey by remember {
        mutableStateOf("")
    }

    OutlinedTextField(
        modifier = modifier,
        value = apiKey,
        label = { Text("API Key") },
        onValueChange = { value ->
            apiKey = value
        })
}
