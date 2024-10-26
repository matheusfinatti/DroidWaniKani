package com.mfinatti.wanikanisimple.core.network.retrofit

import android.util.Log
import com.mfinatti.wanikanisimple.Consts
import com.mfinatti.wanikanisimple.core.network.RemoteWKDataSource
import com.mfinatti.wanikanisimple.core.network.data.CollectionDTO
import com.mfinatti.wanikanisimple.core.network.data.ResponseDTO
import com.mfinatti.wanikanisimple.core.network.data.model.UserDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.SubjectDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

internal interface RetrofitWKServiceApi {

    @GET("user")
    suspend fun getUser(
        @Header("Authorization") authHeader: String
    ): Response<ResponseDTO<UserDTO>>

    @GET("subjects")
    suspend fun getSubjects(
        @Query("levels") level: Int,
    ): Response<CollectionDTO<ResponseDTO<SubjectDTO>>>
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

    override suspend fun getSubjects(level: Int): Result<List<SubjectDTO>> =
        runCatching {
            val response = wkApi.getSubjects(level)
            if (response.isSuccessful) {
                val body = response.body() ?: throw IllegalStateException("Empty response body")
                val subjects = body.data.map { it.data }
                subjects
            } else {
                val error = response.errorBody()?.string() ?: "Empty error body"
                Log.i(Consts.TAG, "Error: $error")
                throw IllegalStateException("Error fetching subjects: $error")
            }
        }
}
