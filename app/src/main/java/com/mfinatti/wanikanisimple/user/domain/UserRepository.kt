package com.mfinatti.wanikanisimple.user.domain

interface UserRepository {

    suspend fun getUser(apiKey: ApiKey): Result<User>
}