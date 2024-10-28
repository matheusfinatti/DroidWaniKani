package com.mfinatti.wanikanisimple.splash.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfinatti.wanikanisimple.Consts
import com.mfinatti.wanikanisimple.login.domain.UserManager
import com.mfinatti.wanikanisimple.models.types.UserId
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val userManager: UserManager,
) : ViewModel() {

    private val _loadingState = MutableStateFlow<LoadingState>(LoadingState.Loading)
    val loadingState: StateFlow<LoadingState> = _loadingState

    init {
        viewModelScope.launch {
            userManager.getUserId().fold(
                onSuccess = { userId ->
                    Log.d(Consts.TAG, "On Success $userId")
                    userManager.getUser(userId)
                        .catch { error ->
                            _loadingState.update { LoadingState.Error(error) }
                        }.collect { user ->
                            Log.d(Consts.TAG, "User $user")
                            _loadingState.update { LoadingState.Loaded(user) }
                        }
                },
                onFailure = { error ->
                    Log.d(Consts.TAG, "on Error $error")
                    _loadingState.update {
                        when (error) {
                            is UserId.Errors.Null -> LoadingState.NotLoggedIn
                            else -> LoadingState.Error(error)
                        }
                    }
                }
            )
        }
    }
}
