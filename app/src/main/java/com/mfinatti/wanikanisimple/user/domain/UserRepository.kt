package com.mfinatti.wanikanisimple.user.domain

import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUser(apiKey: ApiKey): Flow<User>

    suspend fun fetchUser(apiKey: ApiKey): Result<User>

    suspend fun storeApiKey(apiKey: ApiKey): Result<Unit>

    fun getUserApiKey(): Result<ApiKey>

    suspend fun storeUser(user: User): Result<Unit>
}