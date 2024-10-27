package com.mfinatti.wanikanisimple.home.data

import com.mfinatti.wanikanisimple.core.network.RemoteWKDataSource
import com.mfinatti.wanikanisimple.core.network.data.model.summary.SummaryDTO
import com.mfinatti.wanikanisimple.home.data.mapper.toSummary
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class SummaryRepositoryImplTest {

    val remoteDataSource = mockk<RemoteWKDataSource>()

    val sut = SummaryRepositoryImpl(
        remoteDataSource = remoteDataSource
    )

    @Test
    fun `when fetching summary, it should return a successful result of the summary`() = runTest {
        // Arrange
        val summaryDto = SummaryDTO(
            lessons = emptyList(),
            reviews = emptyList(),
            nextReviewsAt = null,
        )
        coEvery { remoteDataSource.getSummary() } returns Result.success(summaryDto)

        // Act
        val result = sut.fetchSummary()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(summaryDto.toSummary().getOrThrow(), result.getOrThrow())
    }
}
