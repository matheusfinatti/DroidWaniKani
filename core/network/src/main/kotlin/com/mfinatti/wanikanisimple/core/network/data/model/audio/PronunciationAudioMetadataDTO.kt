package com.mfinatti.wanikanisimple.core.network.data.model.audio

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PronunciationAudioMetadataDTO(
    @Json(name = "gender")
    val gender: String,
    @Json(name = "source_id")
    val sourceId: Int,
    @Json(name = "pronunciation")
    val pronunciation: String,
    @Json(name = "voice_actor_id")
    val voiceActorId: Int,
    @Json(name = "voice_actor_name")
    val voiceActorName: String,
    @Json(name = "voice_description")
    val voiceDescription: String,
)
