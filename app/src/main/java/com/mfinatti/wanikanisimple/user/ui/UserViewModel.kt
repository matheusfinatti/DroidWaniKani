package com.mfinatti.wanikanisimple.user.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfinatti.wanikanisimple.Consts
import com.mfinatti.wanikanisimple.user.domain.ApiKey.Companion.into
import com.mfinatti.wanikanisimple.user.domain.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val userRepository: UserRepository,
) : ViewModel() {

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Init)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(apiKey: String) {
        Log.d(Consts.TAG, "login($apiKey)")
        val apiKey = apiKey.into().getOrElse { error ->
            _loginState.value = LoginState.Error(error)
            return
        }

        Log.d(Consts.TAG, "ApiKey: ${apiKey.value}")
        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            userRepository.getUser(apiKey)
                .onFailure { error ->
                    _loginState.value = LoginState.Error(error)
                }
                .onSuccess { user ->
                    _loginState.value = LoginState.Success(user)
                }
        }
    }
}