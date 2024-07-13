package com.mfinatti.wanikanisimple.user.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class UserDTO(
    val id: String,
    val username: String,
    val level: Int,
    @Json(name = "profile_url")
    val profileUrl: String,
    @Json(name = "started_at")
    val startedAt: String,
    @Json(name = "current_vacation_started_at")
    val currentVacationStartedAt: String?,
    val subscription: SubscriptionDTO,
)
