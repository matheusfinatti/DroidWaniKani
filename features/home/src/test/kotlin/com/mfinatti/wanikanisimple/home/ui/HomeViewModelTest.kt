package com.mfinatti.wanikanisimple.home.ui

import app.cash.turbine.test
import com.mfinatti.wanikanisimple.home.domain.GetSummaryUseCase
import com.mfinatti.wanikanisimple.home.domain.state.SummaryState
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test

class HomeViewModelTest {

    val getSummaryUseCase = mockk<GetSummaryUseCase>()

    @BeforeTest
    fun setUp() {
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @AfterTest
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun uponInitialization_fetchSummaries() = runTest {
        // Arrange
        every { getSummaryUseCase.execute() } returns flowOf(
            SummaryState.Loading, SummaryState.Success(mockk())
        )

        // Act
        val sut = HomeViewModel(getSummaryUseCase)

        // Assert
        sut.summaryState.test {
            assert(awaitItem() is SummaryState.Loading)
            assert(awaitItem() is SummaryState.Success)
        }
    }
}
