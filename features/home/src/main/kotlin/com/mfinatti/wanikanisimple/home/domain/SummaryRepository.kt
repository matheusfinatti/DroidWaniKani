package com.mfinatti.wanikanisimple.home.domain

import com.mfinatti.wanikanisimple.models.data.Summary

/**
 * Defines the contract for fetching summary data.
 */
interface SummaryRepository {

    /**
     * Fetches the summary data.
     * @return A [Result] containing the [Summary] data.
     */
    suspend fun fetchSummary(): Result<Summary>
}
