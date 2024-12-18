package com.mfinatti.wanikanisimple.login.data.mapper

import com.mfinatti.wanikanisimple.user.SubscriptionEntity
import com.mfinatti.wanikanisimple.core.network.data.model.SubscriptionDTO
import com.mfinatti.wanikanisimple.models.types.Subscription
import com.mfinatti.wanikanisimple.models.types.SubscriptionType
import com.mfinatti.wanikanisimple.models.types.UserId
import java.time.Instant

fun SubscriptionDTO.toSubscription() = kotlin.runCatching {
    when (SubscriptionType.valueOf(type)) {
        SubscriptionType.free -> Subscription.Free(
            active = active,
        )

        SubscriptionType.recurring -> Subscription.Recurring(
            active = active,
            periodEndsAt = Instant.parse(periodEndsAt)
        )

        SubscriptionType.lifetime -> Subscription.Lifetime(
            active = active,
        )
    }
}

fun SubscriptionEntity.toSubscription() = kotlin.runCatching {
    when (SubscriptionType.valueOf(type)) {
        SubscriptionType.free -> Subscription.Free(
            active = active,
        )

        SubscriptionType.recurring -> Subscription.Recurring(
            active = active,
            periodEndsAt = Instant.parse(periodEndsAt)
        )

        SubscriptionType.lifetime -> Subscription.Lifetime(
            active = active,
        )
    }
}

fun Subscription.toEntity(userId: UserId) = kotlin.runCatching {
    SubscriptionEntity(
        userId = userId.value,
        active = active,
        maxLevelGranted = maxLevelGranted.value,
        periodEndsAt = periodEndsAt?.toString(),
        type = type.name.lowercase(),
    )
}
