package com.mfinatti.wanikanisimple.user.data.local

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class UserEntity(
    @PrimaryKey val id: String,
    val username: String,
    val level: Int,
    @ColumnInfo(name = "profile_url")
    val profileUrl: String,
    @ColumnInfo(name = "started_at")
    val startedAt: String,
    @ColumnInfo("current_vacation_started_at")
    val currentVacationStartedAt: String?,
    @Embedded
    val subscription: SubscriptionEntity,
)