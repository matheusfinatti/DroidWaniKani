package com.mfinatti.wanikanisimple.user.data

import android.util.Log
import com.mfinatti.wanikanisimple.Consts
import com.mfinatti.wanikanisimple.user.data.mapper.toUser
import com.mfinatti.wanikanisimple.user.data.remote.UserService
import com.mfinatti.wanikanisimple.models.types.ApiKey
import com.mfinatti.wanikanisimple.models.data.User
import com.mfinatti.wanikanisimple.models.types.UserId
import com.mfinatti.wanikanisimple.user.domain.UserRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService,
    private val userStorage: UserStorage,
) : UserRepository {

    override fun getUser(userId: UserId): Flow<User> =
        userStorage.getUser(userId.value)

    override suspend fun fetchUser(apiKey: ApiKey): Result<User> =
        runCatching {
            val authHeader = "Bearer ${apiKey.value}"
            val response = userService.getUser(authHeader)
            if (response.isSuccessful) {
                val body = response.body() ?: throw IllegalStateException("Empty response body")
                val user = body.data.toUser().getOrThrow()
                user
            } else {
                val error = response.errorBody()?.string() ?: "Empty error body"
                throw IllegalStateException("Error fetching user: $error")
            }
        }

    override suspend fun storeApiKey(apiKey: ApiKey): Result<Unit> =
        runCatching {
            userStorage.storeUserApiKey(apiKey.value)
        }

    override fun getUserApiKey(): Result<ApiKey> =
        ApiKey.from(userStorage.getUserApiKey())

    override fun getUserId(): Result<UserId> =
        UserId.from(userStorage.getUserId())

    override suspend fun storeUser(user: User): Result<Unit> = runCatching {
        Log.d(Consts.TAG, "Store User: $user")
        userStorage.storeUser(user)
    }
}
