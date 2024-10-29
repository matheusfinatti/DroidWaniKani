package com.mfinatti.wanikanisimple.login.di

import android.content.SharedPreferences
import com.mfinatti.wanikanisimple.login.data.UserStorage
import com.mfinatti.wanikanisimple.user.UserDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserStorageModule {

    @Provides
    @Singleton
    fun provideUserStorage(prefs: SharedPreferences, dao: UserDao): UserStorage =
        UserStorage(prefs, dao)
}
