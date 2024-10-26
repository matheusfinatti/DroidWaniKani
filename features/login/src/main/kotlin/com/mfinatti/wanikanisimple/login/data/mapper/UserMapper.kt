package com.mfinatti.wanikanisimple.login.data.mapper

import com.mfinatti.wanikanisimple.core.network.data.model.UserDTO
import com.mfinatti.wanikanisimple.models.types.Level
import com.mfinatti.wanikanisimple.models.types.SubscriptionType
import com.mfinatti.wanikanisimple.models.data.User
import com.mfinatti.wanikanisimple.models.types.UserId
import com.mfinatti.wanikanisimple.user.UserEntity
import java.time.Instant

fun com.mfinatti.wanikanisimple.user.UserWithSubscription.toUser() = kotlin.runCatching {
    User(
        id = UserId.from(user.id).getOrThrow(),
        username = user.username,
        level = Level.from(user.level, SubscriptionType.valueOf(subscription.type)).getOrThrow(),
        profileUrl = user.profileUrl,
        startedAt = Instant.parse(user.startedAt),
        currentVacationStartedAt = user.currentVacationStartedAt?.let { Instant.parse(it) },
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
    )
}
