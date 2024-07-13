package com.mfinatti.wanikanisimple.user.di

import android.content.SharedPreferences
import com.mfinatti.wanikanisimple.data.local.AppDatabase
import com.mfinatti.wanikanisimple.user.data.UserStorage
import com.mfinatti.wanikanisimple.user.data.local.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UserStorageModule {

    @Provides
    fun provideUserStorage(prefs: SharedPreferences, dao: UserDao): UserStorage =
        UserStorage(prefs, dao)

    @Provides
    fun provideUserDao(database: AppDatabase): UserDao =
        database.userDao()
}
