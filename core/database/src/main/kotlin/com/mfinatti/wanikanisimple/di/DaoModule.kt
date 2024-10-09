package com.mfinatti.wanikanisimple.di

import com.mfinatti.wanikanisimple.data.AppDatabase
import com.mfinatti.wanikanisimple.user.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DaoModule {

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao =
        database.userDao()
}
