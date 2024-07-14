package com.mfinatti.wanikanisimple.user.data.local

import androidx.room.Embedded
import androidx.room.Relation

data class UserWithSubscription(
    @Embedded val user: UserEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "user_id"
    )
    val subscription: SubscriptionEntity,
)