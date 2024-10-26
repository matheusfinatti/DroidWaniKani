package com.mfinatti.wanikanisimple.core.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PagesDTO(
    @Json(name = "per_page")
    val perPage: Int,
    @Json(name = "next_url")
    val nextUrl: String?,
    @Json(name = "previous_url")
    val previousUrl: String?,
)
