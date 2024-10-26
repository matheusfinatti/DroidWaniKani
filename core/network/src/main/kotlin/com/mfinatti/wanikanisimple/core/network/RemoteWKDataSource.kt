package com.mfinatti.wanikanisimple.core.network

import com.mfinatti.wanikanisimple.core.network.data.model.UserDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.SubjectDTO

/**
 * Interface outlining the contract to WK API.
 */
interface RemoteWKDataSource {

    suspend fun getUser(apiKey: String): Result<UserDTO>

    suspend fun getSubjects(apiKey: String, level: Int): Result<List<SubjectDTO>>
}