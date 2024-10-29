package com.mfinatti.wanikanisimple

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfinatti.wanikanisimple.login.data.UserStorage
import com.mfinatti.wanikanisimple.models.data.User
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val userStorage: UserStorage,
) : ViewModel() {

    val user: StateFlow<User?> = userStorage.userIdFlow.flatMapLatest { userId ->
        Log.d("MATHEUS", "userId: $userId")
        if (userId != null) {
            userStorage.getUser(userId)
        } else {
            flowOf(null)
        }
    }.stateIn(
        scope = viewModelScope,
        initialValue = null,
        started = SharingStarted.WhileSubscribed(STOP_TIMEOUT_MS)
    )

    companion object {
        private const val STOP_TIMEOUT_MS = 15_000L
    }
}
