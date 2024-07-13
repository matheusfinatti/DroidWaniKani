package com.mfinatti.wanikanisimple.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mfinatti.wanikanisimple.user.data.local.SubscriptionEntity
import com.mfinatti.wanikanisimple.user.data.local.UserDao
import com.mfinatti.wanikanisimple.user.data.local.UserEntity

@Database(entities = [UserEntity::class, SubscriptionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}