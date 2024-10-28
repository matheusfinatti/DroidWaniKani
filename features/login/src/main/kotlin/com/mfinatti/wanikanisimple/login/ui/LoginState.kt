package com.mfinatti.wanikanisimple.login.ui

sealed interface LoginState {
    data object Init : LoginState
    data object Loading : LoginState
    data class Error(val error: Throwable) : LoginState
    data class Success(val user: com.mfinatti.wanikanisimple.models.data.User) : LoginState
}
