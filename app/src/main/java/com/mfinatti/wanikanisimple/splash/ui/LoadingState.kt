package com.mfinatti.wanikanisimple.splash.ui

import com.mfinatti.wanikanisimple.models.data.User

sealed interface LoadingState {

    data object Loading : LoadingState
    data class Error(val error: Throwable) : LoadingState
    data class Loaded(val user: User) : LoadingState
    data object NotLoggedIn : LoadingState
}
