package com.mfinatti.wanikanisimple.user.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "subscription")
data class SubscriptionEntity(
    @PrimaryKey
    @ColumnInfo(name = "user_id")
    val userId: String,
    val active: Boolean,
    @ColumnInfo(name = "max_level_granted")
    val maxLevelGranted: Int,
    @ColumnInfo(name = "period_ends_at")
    val periodEndsAt: String?,
    val type: String,
)
