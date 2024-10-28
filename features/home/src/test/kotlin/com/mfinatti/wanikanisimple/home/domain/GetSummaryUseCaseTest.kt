package com.mfinatti.wanikanisimple.home.domain

import app.cash.turbine.test
import com.mfinatti.wanikanisimple.home.domain.state.SummaryState
import com.mfinatti.wanikanisimple.models.data.Summary
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals

class GetSummaryUseCaseTest {

    val repository = mockk<SummaryRepository>()
    val sut = GetSummaryUseCase(repository)

    @Test
    fun whenExecuting_thenReturnSuccessState() = runTest {
        // Arrange
        val mockSummary = mockk<Summary>()
        coEvery { repository.fetchSummary() } returns Result.success(mockSummary)

        // Act
        sut.execute().test {
            // Assert
            assert(awaitItem() is SummaryState.Loading)
            val successItem = awaitItem()
            assert(successItem is SummaryState.Success)
            assertEquals(mockSummary, (successItem as SummaryState.Success).summary)
            awaitComplete()
        }
    }

    @Test
    fun whenExecuting_givenError_thenReturnErrorState() = runTest {
        // Arrange
        coEvery { repository.fetchSummary() } returns Result.failure(mockk())

        // Act
        sut.execute().test {
            // Assert
            assert(awaitItem() is SummaryState.Loading)
            assert(awaitItem() is SummaryState.Error)
            awaitComplete()
        }
    }
}
