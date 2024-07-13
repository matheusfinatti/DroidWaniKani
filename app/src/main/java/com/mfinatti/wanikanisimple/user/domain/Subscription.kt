package com.mfinatti.wanikanisimple.user.domain

import java.time.Instant

sealed interface Subscription {
    val active: Boolean
    val type: SubscriptionType
    val maxLevelGranted: Level
    val periodEndsAt: Instant?

    data class Free(
        override val active: Boolean,
    ) : Subscription {
        override val periodEndsAt: Instant? = null
        override val type: SubscriptionType = SubscriptionType.free
        override val maxLevelGranted: Level = SubscriptionType.free.maxLevel
    }

    data class Recurring(
        override val active: Boolean,
        override val periodEndsAt: Instant,
    ) : Subscription {
        override val type: SubscriptionType = SubscriptionType.recurring
        override val maxLevelGranted: Level = SubscriptionType.recurring.maxLevel
    }

    data class Lifetime(
        override val active: Boolean,
    ) : Subscription {
        override val periodEndsAt: Instant? = null
        override val type: SubscriptionType = SubscriptionType.lifetime
        override val maxLevelGranted: Level = SubscriptionType.lifetime.maxLevel
    }
}
