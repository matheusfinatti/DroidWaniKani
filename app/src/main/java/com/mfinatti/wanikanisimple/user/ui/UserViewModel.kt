package com.mfinatti.wanikanisimple.user.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfinatti.wanikanisimple.models.types.ApiKey
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
        val apiKey = ApiKey.from(apiKey).getOrElse { error ->
            _loginState.value = LoginState.Error(error)
            return
        }

        _loginState.value = LoginState.Loading

        viewModelScope.launch {
            userRepository.fetchUser(apiKey)
                .onFailure { error ->
                    _loginState.value = LoginState.Error(error)
                }
                .onSuccess { user ->
                    // Store ApiKey in storage
                    userRepository.storeApiKey(apiKey)
                    userRepository.storeUser(user)
                    // Store User in db
                    _loginState.value = LoginState.Success(user)
                }
        }
    }
}
