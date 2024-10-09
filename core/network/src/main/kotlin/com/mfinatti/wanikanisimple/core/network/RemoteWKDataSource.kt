package com.mfinatti.wanikanisimple.core.network

import com.mfinatti.wanikanisimple.core.network.data.model.UserDTO

/**
 * Interface outlining the contract to WK API.
 */
interface RemoteWKDataSource {

    suspend fun getUser(apiKey: String): Result<UserDTO>
}
