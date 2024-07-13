package com.mfinatti.wanikanisimple.user.data

import com.mfinatti.wanikanisimple.user.domain.ApiKey
import com.mfinatti.wanikanisimple.user.domain.User
import com.mfinatti.wanikanisimple.user.domain.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val userService: UserService
) : UserRepository {

    override suspend fun getUser(apiKey: ApiKey): Result<User> =
        runCatching {
            val authHeader = "Bearer ${apiKey.value}"
            val response = userService.getUser(authHeader)
            if (response.isSuccessful) {
                val body = response.body() ?: throw IllegalStateException("Empty response body")
                val user = User(body.data.username)
                user
            } else {
                val error = response.errorBody()?.string() ?: "Empty error body"
                throw IllegalStateException("Error fetching user: $error")
            }
        }
}
