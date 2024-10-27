package com.mfinatti.wanikanisimple.home.data

import com.mfinatti.wanikanisimple.core.network.RemoteWKDataSource
import com.mfinatti.wanikanisimple.home.data.mapper.toSummary
import com.mfinatti.wanikanisimple.home.domain.SummaryRepository
import com.mfinatti.wanikanisimple.models.data.Summary
import javax.inject.Inject

class SummaryRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteWKDataSource,
) : SummaryRepository {

    override suspend fun fetchSummary(): Result<Summary> = runCatching {
        val summary = remoteDataSource.getSummary().getOrThrow()
        return summary.toSummary()
    }
}
