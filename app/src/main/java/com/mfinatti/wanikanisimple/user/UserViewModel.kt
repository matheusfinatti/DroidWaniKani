package com.mfinatti.wanikanisimple.user

import android.util.Log
import androidx.lifecycle.ViewModel
import com.mfinatti.wanikanisimple.Consts
import com.mfinatti.wanikanisimple.user.ApiKey.Companion.into
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor() : ViewModel() {

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Init)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(apiKey: String) {
        Log.d(Consts.TAG, "login($apiKey)")
        val apiKey = apiKey.into().getOrElse { error ->
            _loginState.value = LoginState.Error(error)
            return
        }

        Log.d(Consts.TAG, "ApiKey: ${apiKey.value}")
        // TODO: Send request to /user/ to verify the key is valid.
        _loginState.value = LoginState.Loading
        _loginState.value = LoginState.Success
    }
}