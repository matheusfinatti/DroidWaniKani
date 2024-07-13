package com.mfinatti.wanikanisimple.user

sealed interface LoginState {
    data object Init : LoginState
    data object Loading : LoginState
    data class Error(val error: Throwable) : LoginState
    data object Success : LoginState
}