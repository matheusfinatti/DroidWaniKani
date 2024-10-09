package com.mfinatti.wanikanisimple.user.data

import android.content.SharedPreferences
import android.util.Log
import com.mfinatti.wanikanisimple.Consts
import com.mfinatti.wanikanisimple.user.data.local.UserDao
import com.mfinatti.wanikanisimple.user.data.mapper.toEntity
import com.mfinatti.wanikanisimple.user.data.mapper.toUser
import com.mfinatti.wanikanisimple.models.data.User
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class UserStorage @Inject constructor(
    private val preferences: SharedPreferences,
    private val userDao: UserDao,
) {

    fun getUser(userId: String): Flow<User> = userDao.getUser(userId)
        .map { entity ->
            Log.d(Consts.TAG, "$entity")
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

    private object Keys {
        const val API_KEY = "USER_API_KEY"
        const val USER_ID = "USER_ID"
    }
}
