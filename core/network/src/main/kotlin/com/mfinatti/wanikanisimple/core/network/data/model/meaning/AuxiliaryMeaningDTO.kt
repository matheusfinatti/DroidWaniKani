package com.mfinatti.wanikanisimple.core.network.data.model.meaning

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AuxiliaryMeaningDTO(
    @Json(name = "meaning")
    val meaning: String,
    @Json(name = "type")
    val type: String,
)
