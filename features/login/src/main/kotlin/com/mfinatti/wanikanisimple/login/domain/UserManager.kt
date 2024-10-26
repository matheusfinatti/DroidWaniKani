package com.mfinatti.wanikanisimple.login.domain

import com.mfinatti.wanikanisimple.models.data.User
import com.mfinatti.wanikanisimple.models.types.ApiKey
import com.mfinatti.wanikanisimple.models.types.UserId
import kotlinx.coroutines.flow.Flow

/**
 * Manages user data.
 * Provides facilities to fetch and store user data.
 */
interface UserManager {

    /**
     * Gets a currently logged in user - if any.
     * @param userId The user ID.
     * @return A [Flow] of [User].
     */
    fun getUser(userId: UserId): Flow<User>

    /**
     * Fetches user data from the API.
     * @param apiKey The API key. This should be obtained by the user in WaniKani web interface.
     * @return A [Result] of [User].
     */
    suspend fun fetchUser(apiKey: ApiKey): Result<User>

    /**
     * Stores the API key.
     * @param apiKey The API key to be stored.
     * @return A [Result] if the operation is successful or not.
     */
    suspend fun storeApiKey(apiKey: ApiKey): Result<Unit>

    /**
     * Gets the API key from local storage.
     * @return A [Result] of [ApiKey].
     */
    fun getUserApiKey(): Result<ApiKey>

    /**
     * Gets the user ID from local storage.
     * @return A [Result] of [UserId].
     */
    fun getUserId(): Result<UserId>

    /**
     * Stores the user object in the database.
     * @param user The user to be stored.
     * @return A [Result] with the result of the operation.
     */
    suspend fun storeUser(user: User): Result<Unit>
}
