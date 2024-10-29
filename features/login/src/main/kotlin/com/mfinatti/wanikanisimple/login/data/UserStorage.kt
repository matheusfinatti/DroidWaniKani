package com.mfinatti.wanikanisimple.login.data

import android.content.SharedPreferences
import com.mfinatti.wanikanisimple.Keys
import com.mfinatti.wanikanisimple.login.data.mapper.toEntity
import com.mfinatti.wanikanisimple.login.data.mapper.toUser
import com.mfinatti.wanikanisimple.models.data.User
import com.mfinatti.wanikanisimple.user.UserDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserStorage @Inject constructor(
    private val preferences: SharedPreferences,
    private val userDao: UserDao,
) {

    private val _userIdFlow: MutableStateFlow<String?> = MutableStateFlow(
        preferences.getString(Keys.USER_ID, null)
    )
    val userIdFlow: Flow<String?> = _userIdFlow

    fun getUser(userId: String): Flow<User> = userDao.getUser(
        userId
    )
        .map { entity ->
            entity.toUser().getOrThrow()
        }

    suspend fun storeUser(user: User) {
        user.toEntity().map { entity ->
            userDao.insertUser(entity)
        }
        user.subscription.toEntity(user.id).map { entity ->
            userDao.insertSubscription(entity)
        }
        preferences.edit()
            .putString(Keys.USER_ID, user.id.value)
            .apply()

        _userIdFlow.emit(user.id.value)
    }

    fun storeUserApiKey(apiKey: String) {
        preferences.edit()
            .putString(Keys.API_KEY, apiKey)
            .apply()
    }

    fun getUserApiKey(): String? =
        preferences.getString(Keys.API_KEY, null)

    fun getUserId(): String? =
        preferences.getString(Keys.USER_ID, null)
}
