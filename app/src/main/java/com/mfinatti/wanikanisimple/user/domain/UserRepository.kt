package com.mfinatti.wanikanisimple.user.domain

import com.mfinatti.wanikanisimple.models.data.User
import com.mfinatti.wanikanisimple.models.types.ApiKey
import com.mfinatti.wanikanisimple.models.types.UserId
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(userId: UserId): Flow<User>

    suspend fun fetchUser(apiKey: ApiKey): Result<User>

    suspend fun storeApiKey(apiKey: ApiKey): Result<Unit>

    fun getUserApiKey(): Result<ApiKey>

    fun getUserId(): Result<UserId>

    suspend fun storeUser(user: User): Result<Unit>
}