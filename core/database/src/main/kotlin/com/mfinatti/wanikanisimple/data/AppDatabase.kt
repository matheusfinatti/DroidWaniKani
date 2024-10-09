package com.mfinatti.wanikanisimple.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.mfinatti.wanikanisimple.user.SubscriptionEntity
import com.mfinatti.wanikanisimple.user.UserDao
import com.mfinatti.wanikanisimple.user.UserEntity

@Database(entities = [UserEntity::class, SubscriptionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}
