package com.mfinatti.wanikanisimple.user.ui

import com.mfinatti.wanikanisimple.models.data.User

sealed interface LoginState {
    data object Init : LoginState
    data object Loading : LoginState
    data class Error(val error: Throwable) : LoginState
    data class Success(val user: User) : LoginState
}