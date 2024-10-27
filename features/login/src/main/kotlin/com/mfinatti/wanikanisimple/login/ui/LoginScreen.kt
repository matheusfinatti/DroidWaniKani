@file:OptIn(ExperimentalMaterial3Api::class)

package com.mfinatti.wanikanisimple.login.ui

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Warning
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.mfinatti.wanikanisimple.Consts
import com.mfinatti.wanikanisimple.login.R
import androidx.compose.runtime.getValue

@Composable
fun LoginScreen(
    viewModel: LoginViewModel,
    onLoginSuccess: () -> Unit,
) {
    Log.d(Consts.TAG, "LoginScreen Composition")
    val loginState by viewModel.loginState.collectAsStateWithLifecycle()

    val (apiKey, setApiKey) = remember {
        mutableStateOf("")
    }

    LoginView(Modifier, loginState, apiKey, setApiKey, onSubmitClicked = {
        viewModel.login(apiKey)
    }) {
       onLoginSuccess()
    }
}

@Composable
private fun LoginView(
    modifier: Modifier,
    loginState: LoginState,
    apiKey: String,
    setApiKey: (String) -> Unit,
    onSubmitClicked: () -> Unit,
    onLoginSuccess: () -> Unit,
) {
    Log.d(Consts.TAG, "LoginView Composition")

    Box(
        modifier = modifier
            .fillMaxSize()
            .paint(
                painter = painterResource(R.drawable.features_login_background),
                sizeToIntrinsics = true,
                contentScale = ContentScale.FillBounds,
            )

    ) {
        Image(
            painter = painterResource(R.drawable.features_login_wanikani_logo),
            contentDescription = null,
            alignment = Alignment.TopCenter,
            modifier = Modifier
                .padding(top = 24.dp)
                .padding(32.dp)
        )
        Column(
            modifier = Modifier
                .align(Alignment.Center)
                .fillMaxWidth()
                .padding(16.dp)
                .background(
                    color = Color.White.copy(alpha = 0.5f),
                    shape = RoundedCornerShape(16.dp)
                )
                .padding(8.dp)
        ) {
            Log.d(Consts.TAG, "LoginState: $loginState")
            when (loginState) {
                is LoginState.Init -> LoginInput(
                    errorMessage = null,
                    apiKey,
                    setApiKey,
                    submitOnClick = onSubmitClicked,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                is LoginState.Loading -> CircularProgressIndicator()
                is LoginState.Error -> LoginInput(
                    errorMessage = loginState.error.message ?: "Unknown error",
                    apiKey = apiKey,
                    setApiKey = setApiKey,
                    submitOnClick = onSubmitClicked,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )

                is LoginState.Success -> onLoginSuccess()
            }
        }
    }
}

@Composable
private fun ColumnScope.LoginInput(
    errorMessage: String?,
    apiKey: String,
    setApiKey: (String) -> Unit,
    submitOnClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = stringResource(R.string.feature_login_input_prompt),
        modifier = modifier,
        fontWeight = FontWeight.Bold,
    )
    ApiKeyInput(apiKey, setApiKey, modifier = modifier)
    ErrorLine(errorMessage = errorMessage ?: "", modifier = modifier)
    Spacer(modifier.size(16.dp))
    Button(
        onClick = submitOnClick,
        modifier = modifier,
    ) {
        Text(stringResource(R.string.feature_login_button_submit))
    }
}

@Composable
private fun ApiKeyInput(
    apiKey: String,
    setApiKey: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    OutlinedTextField(
        modifier = modifier
            .padding(8.dp),
        value = apiKey,
        label = { Text(stringResource(R.string.feature_login_input_placeholder)) },
        onValueChange = { value ->
            setApiKey(value)
        })
}

@Composable
private fun ErrorLine(
    errorMessage: String,
    modifier: Modifier = Modifier,
) {
    Row(modifier = modifier.padding(2.dp)) {
        if (errorMessage.isNotBlank()) {
            Icon(
                imageVector = Icons.TwoTone.Warning,
                contentDescription = null,
                tint = Color.Red,
            )
        }
        Text(
            text = errorMessage,
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreviewInit() {
    LoginView(
        modifier = Modifier,
        loginState = LoginState.Init,
        apiKey = "",
        setApiKey = {},
        onSubmitClicked = {},
        onLoginSuccess = {},
    )
}

@Preview(showBackground = true)
@Composable
fun LoginViewPreviewError() {
    LoginView(
        modifier = Modifier,
        loginState = LoginState.Error(error = IllegalStateException("Something went wrong!")),
        apiKey = "",
        setApiKey = {},
        onSubmitClicked = {},
        onLoginSuccess = {},
    )
}