package com.mfinatti.wanikanisimple.data

import com.mfinatti.wanikanisimple.user.data.remote.PreferencesDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDTO<T>(
    @Json(name = "object")
    val obj: String,
    val url: String,
    @Json(name = "data_updated_at")
    val dataUpdatedAt: String,
    val data: T,
    val preferences: PreferencesDTO?,
)