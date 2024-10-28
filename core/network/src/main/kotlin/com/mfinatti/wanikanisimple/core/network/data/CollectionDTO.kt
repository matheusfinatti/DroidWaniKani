package com.mfinatti.wanikanisimple.core.network.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CollectionDTO<T>(
    @Json(name = "object")
    val obj: String,
    val url: String,
    val pages: PagesDTO,
    @Json(name = "total_count")
    val totalCount: Int,
    @Json(name = "data_updated_at")
    val dataUpdatedAt: String,
    val data: List<T>,
)
