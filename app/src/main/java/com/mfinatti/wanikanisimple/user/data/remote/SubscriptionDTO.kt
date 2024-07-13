package com.mfinatti.wanikanisimple.user.data.remote

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubscriptionDTO(
    val active: Boolean,
    val type: String,
    @Json(name = "max_level_granted")
    val maxLevelGranted: Int,
    @Json(name = "period_ends_at")
    val periodEndsAt: String?,
)
