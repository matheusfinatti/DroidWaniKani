package com.mfinatti.wanikanisimple.core.network.data.model.image

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterImageMetadataDTO(
    @Json(name = "inline_styles")
    val inlineStyles: Boolean
)
