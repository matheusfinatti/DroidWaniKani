@file:Suppress("MatchingDeclarationName")
package com.mfinatti.wanikanisimple.core.network

import com.mfinatti.wanikanisimple.core.network.adapters.ResponseAdapter
import com.mfinatti.wanikanisimple.core.network.data.model.summary.LessonDTO
import com.mfinatti.wanikanisimple.core.network.retrofit.RetrofitWKService
import com.mfinatti.wanikanisimple.core.network.retrofit.RetrofitWKServiceApi
import com.squareup.moshi.Moshi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RetrofitWKServiceTest {

    private val server = MockWebServer()

    private val moshi = Moshi.Builder()
        .add(ResponseAdapter(Moshi.Builder().build()))
        .build()

    private lateinit var sut: RetrofitWKService

    @BeforeTest
    fun setup() {
        val api = Retrofit.Builder()
            .baseUrl(server.url("/"))
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(RetrofitWKServiceApi::class.java)

        sut = RetrofitWKService(api)
    }

    @AfterTest
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun whenFetchingSummary_ifResponseIsSuccessful_returnSummary() = runTest {
        // Arrange
        val response = MockResponse()
            .setResponseCode(200)
            .setBody(readFileFromResources("summary.response.ok.json"))

        server.enqueue(response)

        // Act
        val result = sut.getSummary()

        // Assert
        assertTrue(result.isSuccess)
        assertEquals(
            LessonDTO(
                availableAt = "2018-04-11T21:00:00.000000Z",
                subjectIds = listOf(25, 26),
            ),
            result.getOrNull()?.lessons?.first()
        )
    }

    @Test
    fun whenFetchingSummary_ifResponseIsNotSuccessful_returnFailure() = runTest {
        // Arrange
        val response = MockResponse()
            .setResponseCode(400)

        server.enqueue(response)

        // Act
        val result = sut.getSummary()

        // Assert
        assertTrue(result.isFailure)
    }

    @Test
    fun whenFetchingSummary_ifResponseBodyIsEmpty_returnFailure() = runTest {
        // Arrange
        val response = MockResponse()
            .setResponseCode(200)

        server.enqueue(response)

        // Act
        val result = sut.getSummary()

        // Assert
        assertTrue(result.isFailure)
    }
}

private fun readFileFromResources(fileName: String): String =
    object {}.javaClass.classLoader?.getResourceAsStream(fileName)?.use {
        it.reader(Charsets.UTF_8).readText()
    }!!
