package com.mfinatti.wanikanisimple.models.data

import com.mfinatti.wanikanisimple.models.types.Level
import com.mfinatti.wanikanisimple.models.types.Subscription
import com.mfinatti.wanikanisimple.models.types.UserId
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
