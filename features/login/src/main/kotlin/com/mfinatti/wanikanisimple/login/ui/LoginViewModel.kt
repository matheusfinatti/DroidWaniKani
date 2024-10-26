package com.mfinatti.wanikanisimple.login.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfinatti.wanikanisimple.models.types.ApiKey
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userManager: com.mfinatti.wanikanisimple.login.domain.UserManager,
) : ViewModel() {

    private val _loginState: MutableStateFlow<LoginState> = MutableStateFlow(LoginState.Init)
    val loginState: StateFlow<LoginState> = _loginState

    fun login(apiKey: String) {
        val apiKey = ApiKey.Companion.from(apiKey)
            .getOrElse { error ->
            _loginState.value = LoginState.Error(error)
            return
        }

        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            userManager.fetchUser(apiKey)
                .onFailure { error ->
                    _loginState.value = LoginState.Error(error)
                }
                .onSuccess { user ->
                    // Store ApiKey in storage
                    userManager.storeApiKey(apiKey)
                    userManager.storeUser(user)
                    // Store User in db
                    _loginState.value = LoginState.Success(user)
                }
        }
    }
}
