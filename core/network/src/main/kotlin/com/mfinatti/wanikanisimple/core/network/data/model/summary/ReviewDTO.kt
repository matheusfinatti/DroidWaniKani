package com.mfinatti.wanikanisimple.core.network.data.model.summary

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ReviewDTO(
    @Json(name = "available_at")
    val availableAt: String,
    @Json(name = "subject_ids")
    val subjectIds: List<Int>
)
