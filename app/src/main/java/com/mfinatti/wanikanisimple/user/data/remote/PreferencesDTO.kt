package com.mfinatti.wanikanisimple.user.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PreferencesDTO(
    @Json(name = "default_voice_actor_id")
    val defaultVoiceActorId: Int,
    @Json(name = "extra_study_autoplay_audio")
    val extraStudyAutoplayAudio: Boolean,
    @Json(name = "lessons_autoplay_audio")
    val lessonsAutoplayAudio: Boolean,
    @Json(name = "lessons_batch_size")
    val lessonsBatchSize: Int,
    @Json(name = "lessons_presentation_order")
    val lessonsPresentationOrder: String,
    @Json(name = "reviews_autoplay_audio")
    val reviewsAutoplayAudio: Boolean,
    @Json(name = "reviews_display_srs_indicator")
    val reviewsDisplaySrsIndicator: Boolean,
    @Json(name = "reviews_presentation_order")
    val reviewsPresentationOrder: String,
)
