package com.mfinatti.wanikanisimple.core.network.data.model.image

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterImageDTO(
    @Json(name = "url")
    val url: String,
//    @Json(name = "metadata")
//    val metadata: CharacterImageMetadataDTO,
    @Json(name = "content_type")
    val contentType: String,
)
