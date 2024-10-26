package com.mfinatti.wanikanisimple.login.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mfinatti.wanikanisimple.Consts
import com.mfinatti.wanikanisimple.login.domain.UserManager
import com.mfinatti.wanikanisimple.models.types.ApiKey
import com.mfinatti.wanikanisimple.subject.domain.SubjectRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userManager: UserManager,
    private val subjectRepository: SubjectRepository,
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
//                    _loginState.value = LoginState.Success(user)

                    // Fetch subjects
                    val subjects = subjectRepository.fetchSubjects(apiKey, user)
                    Log.d(Consts.TAG, "Subjects: $subjects")
                }
        }
    }
}
