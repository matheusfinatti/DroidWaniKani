package com.mfinatti.wanikanisimple.core.network

import com.mfinatti.wanikanisimple.core.network.data.model.UserDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.SubjectDTOWrapper
import com.mfinatti.wanikanisimple.core.network.data.model.summary.SummaryDTO

/**
 * Interface outlining the contract to WK API.
 */
interface RemoteWKDataSource {

    suspend fun getUser(apiKey: String): Result<UserDTO>

    suspend fun getSubjects(level: Int): Result<List<SubjectDTOWrapper>>

    suspend fun getSummary(): Result<SummaryDTO>
}
