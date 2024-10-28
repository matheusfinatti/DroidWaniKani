package com.mfinatti.wanikanisimple.models.types

@JvmInline
value class Level private constructor(val value: Int) {

    companion object {
        fun from(value: Int, subscriptionType: SubscriptionType): Result<Level> =
            if (value in 1..subscriptionType.maxLevel.value) {
                Result.success(Level(value))
            } else {
                Result.failure(IllegalArgumentException("Level must be between 1 and ${subscriptionType.maxLevel}"))
            }

        val UnsubscribedMaxLevel = Level(3)
        val SubscribedMaxLevel = Level(60)
    }
}
