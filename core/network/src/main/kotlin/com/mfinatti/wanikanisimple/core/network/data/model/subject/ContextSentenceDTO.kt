package com.mfinatti.wanikanisimple.core.network.data.model.subject

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ContextSentenceDTO(
    @Json(name = "en")
    val en: String,
    @Json(name = "ja")
    val ja: String,
)
