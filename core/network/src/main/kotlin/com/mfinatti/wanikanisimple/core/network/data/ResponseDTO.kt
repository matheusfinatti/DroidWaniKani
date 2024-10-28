package com.mfinatti.wanikanisimple.core.network.data

import com.mfinatti.wanikanisimple.core.network.data.model.PreferencesDTO
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseDTO<T>(
    val id: Int?,
    @Json(name = "object")
    val obj: String,
    val url: String,
    @Json(name = "data_updated_at")
    val dataUpdatedAt: String,
    val data: T,
    val preferences: PreferencesDTO?,
)
