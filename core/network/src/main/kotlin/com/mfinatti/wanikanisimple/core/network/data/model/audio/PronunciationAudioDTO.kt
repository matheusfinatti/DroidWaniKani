package com.mfinatti.wanikanisimple.core.network.data.model.audio

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PronunciationAudioDTO(
    @Json(name = "url")
    val url: String,
    @Json(name = "content_type")
    val contentType: String,
    @Json(name = "metadata")
    val metadata: PronunciationAudioMetadataDTO,
)
