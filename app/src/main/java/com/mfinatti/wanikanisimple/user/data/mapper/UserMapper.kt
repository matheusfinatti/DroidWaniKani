package com.mfinatti.wanikanisimple.user.data.mapper

import com.mfinatti.wanikanisimple.user.data.local.UserEntity
import com.mfinatti.wanikanisimple.user.data.remote.UserDTO
import com.mfinatti.wanikanisimple.user.domain.Level
import com.mfinatti.wanikanisimple.user.domain.SubscriptionType
import com.mfinatti.wanikanisimple.user.domain.User
import com.mfinatti.wanikanisimple.user.domain.UserId
import java.time.Instant

fun UserEntity.toUser() = kotlin.runCatching {
    User(
        id = UserId.from(id).getOrThrow(),
        username = username,
        level = Level.from(level, SubscriptionType.valueOf(subscription.type)).getOrThrow(),
        profileUrl = profileUrl,
        startedAt = Instant.parse(startedAt),
        currentVacationStartedAt = currentVacationStartedAt?.let { Instant.parse(it) },
        subscription = subscription.toSubscription().getOrThrow()
    )
}

fun UserDTO.toUser() = kotlin.runCatching {
    User(
        id = UserId.from(id).getOrThrow(),
        username = username,
        level = Level.from(level, SubscriptionType.valueOf(subscription.type)).getOrThrow(),
        profileUrl = profileUrl,
        startedAt = Instant.parse(startedAt),
        currentVacationStartedAt = currentVacationStartedAt?.let { Instant.parse(it) },
        subscription = subscription.toSubscription().getOrThrow(),
    )
}

fun User.toEntity() = kotlin.runCatching {
    UserEntity(
        id = id.value,
        username = username,
        level = level.value,
        profileUrl = profileUrl,
        startedAt = startedAt.toString(),
        currentVacationStartedAt = currentVacationStartedAt?.toString(),
        subscription = subscription.toEntity(id).getOrThrow(),
    )
}
