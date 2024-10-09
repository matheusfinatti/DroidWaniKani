package com.mfinatti.wanikanisimple.core.network.retrofit

import android.util.Log
import com.mfinatti.wanikanisimple.core.network.RemoteWKDataSource
import com.mfinatti.wanikanisimple.core.network.data.ResponseDTO
import com.mfinatti.wanikanisimple.core.network.data.model.UserDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import javax.inject.Inject

internal interface RetrofitWKServiceApi {

    @GET("user")
    suspend fun getUser(
        @Header("Authorization") authHeader: String
    ): Response<ResponseDTO<UserDTO>>
}

internal class RetrofitWKService(
    private val wkApi: RetrofitWKServiceApi,
) : RemoteWKDataSource {

    override suspend fun getUser(apiKey: String): Result<UserDTO> =
        runCatching {
            val authHeader = "Bearer $apiKey"
            val response = wkApi.getUser(authHeader)
            if (response.isSuccessful) {
                val body = response.body() ?: throw IllegalStateException("Empty response body")
                val user = body.data
                user
            } else {
                val error = response.errorBody()?.string() ?: "Empty error body"
                throw IllegalStateException("Error fetching user: $error")
            }
        }
}
