package com.mfinatti.wanikanisimple.core.network.data.model.meaning

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MeaningDTO(
    @Json(name = "meaning")
    val meaning: String,
    @Json(name = "primary")
    val primary: Boolean,
    @Json(name = "accepted_answer")
    val acceptedAnswer: Boolean,
)
