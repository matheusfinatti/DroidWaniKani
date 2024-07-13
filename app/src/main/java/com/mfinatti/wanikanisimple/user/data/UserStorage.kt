package com.mfinatti.wanikanisimple.user.data

import android.content.SharedPreferences
import com.mfinatti.wanikanisimple.user.data.local.UserDao
import com.mfinatti.wanikanisimple.user.data.mapper.toEntity
import com.mfinatti.wanikanisimple.user.data.mapper.toUser
import com.mfinatti.wanikanisimple.user.domain.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserStorage @Inject constructor(
    private val preferences: SharedPreferences,
    private val userDao: UserDao,
) {

    fun getUser(apiKey: String): Flow<User> = userDao.getUser(apiKey)
        .map { entity -> entity.toUser().getOrThrow() }

    suspend fun storeUser(user: User) {
        user.toEntity().map { entity ->
            userDao.insertUser(entity)
        }
    }

    fun storeUserApiKey(apiKey: String) {
        preferences.edit()
            .putString(Keys.API_KEY, apiKey)
            .apply()
    }

    fun getUserApiKey(): String? =
        preferences.getString(Keys.API_KEY, null)

    private object Keys {
        const val API_KEY = "USER_API_KEY"
    }
}
