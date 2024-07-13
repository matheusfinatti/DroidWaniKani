package com.mfinatti.wanikanisimple.user.domain

import java.time.Instant

data class User(
    val id: UserId,
    val username: String,
    val level: Level,
    val profileUrl: String,
    val startedAt: Instant,
    val currentVacationStartedAt: Instant?,
    val subscription: Subscription,
)
