package com.mfinatti.wanikanisimple.user.domain

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(userId: UserId): Flow<User>

    suspend fun fetchUser(apiKey: ApiKey): Result<User>

    suspend fun storeApiKey(apiKey: ApiKey): Result<Unit>

    fun getUserApiKey(): Result<ApiKey>

    fun getUserId(): Result<UserId>

    suspend fun storeUser(user: User): Result<Unit>
}