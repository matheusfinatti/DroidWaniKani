package com.mfinatti.wanikanisimple.home.domain.state

import com.mfinatti.wanikanisimple.models.data.Summary

/**
 * Represents the state of the fetch summary use case.
 */
sealed interface SummaryState {

    /**
     * Represents the loading state of the fetch summary use case.
     */
    data object Loading : SummaryState

    /**
     * Represents the success state of the fetch summary use case.
     * @param summary The summary data.
     */
    data class Success(val summary: Summary) : SummaryState

    /**
     * Represents the error state of the fetch summary use case.
     * @param message The error message.
     */
    data class Error(val message: String) : SummaryState
}
