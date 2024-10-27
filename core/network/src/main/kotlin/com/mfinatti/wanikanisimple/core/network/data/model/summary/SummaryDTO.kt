package com.mfinatti.wanikanisimple.core.network.data.model.summary

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SummaryDTO(
    @Json(name = "reviews")
    val reviews: List<ReviewDTO>,
    @Json(name = "lessons")
    val lessons: List<LessonDTO>,
    @Json(name = "next_reviews_at")
    val nextReviewsAt: String?,
)
