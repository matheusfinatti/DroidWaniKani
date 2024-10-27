package com.mfinatti.wanikanisimple.home.domain

import com.mfinatti.wanikanisimple.home.domain.state.SummaryState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Represents the use case for fetching summary data.
 */
class GetSummaryUseCase @Inject constructor(
    private val summaryRepository: SummaryRepository,
) {

    /**
     * Executes the use case.
     * @return A [Flow] emitting the state of the fetch summary use case.
     */
    fun execute(): Flow<SummaryState> {
        return flow {
            emit(SummaryState.Loading)

            summaryRepository.fetchSummary().fold(
                onSuccess = { summary ->
                    emit(SummaryState.Success(summary))
                },
                onFailure = { error ->
                    emit(SummaryState.Error(error.message ?: "Unknown error"))
                }
            )
        }
    }
}
