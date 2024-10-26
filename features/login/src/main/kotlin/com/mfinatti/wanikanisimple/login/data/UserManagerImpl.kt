package com.mfinatti.wanikanisimple.login.data

import android.util.Log
import com.mfinatti.wanikanisimple.Consts
import com.mfinatti.wanikanisimple.core.network.RemoteWKDataSource
import com.mfinatti.wanikanisimple.login.data.mapper.toUser
import com.mfinatti.wanikanisimple.login.domain.UserManager
import com.mfinatti.wanikanisimple.models.data.User
import com.mfinatti.wanikanisimple.models.types.ApiKey
import com.mfinatti.wanikanisimple.models.types.UserId
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class UserManagerImpl @Inject constructor(
    private val remoteService: RemoteWKDataSource,
    private val userStorage: UserStorage,
) : UserManager {

    override fun getUser(userId: UserId): Flow<User> =
        userStorage.getUser(userId.value)

    override suspend fun fetchUser(apiKey: ApiKey): Result<User> =
        remoteService.getUser(apiKey.value).mapCatching{ it.toUser().getOrThrow() }

    override suspend fun storeApiKey(apiKey: ApiKey): Result<Unit> =
        runCatching {
            userStorage.storeUserApiKey(apiKey.value)
        }

    override fun getUserApiKey(): Result<ApiKey> =
        ApiKey.Companion.from(userStorage.getUserApiKey())

    override fun getUserId(): Result<UserId> =
        UserId.Companion.from(userStorage.getUserId())

    override suspend fun storeUser(user: User): Result<Unit> = runCatching {
        Log.d(Consts.TAG, "Store User: $user")
        userStorage.storeUser(user)
    }
}
