package com.mfinatti.wanikanisimple.user.data

import com.mfinatti.wanikanisimple.data.ResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header

interface UserService {

    @GET("user")
    suspend fun getUser(
        @Header("Authorization") authHeader: String
    ): Response<ResponseDTO<UserDTO>>
}