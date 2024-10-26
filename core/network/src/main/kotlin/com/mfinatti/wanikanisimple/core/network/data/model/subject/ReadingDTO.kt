package com.mfinatti.wanikanisimple.core.network.data.model.subject

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReadingDTO(
    @Json(name = "reading")
    val reading: String,
    @Json(name = "primary")
    val primary: Boolean,
    @Json(name = "accepted_answer")
    val acceptedAnswer: Boolean,
    @Json(name = "type")
    val type: String?, // TODO: Create a separate Kanji Reading/Vocab reading type
)
