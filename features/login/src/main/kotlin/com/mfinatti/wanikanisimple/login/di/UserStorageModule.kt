package com.mfinatti.wanikanisimple.login.di

import android.content.SharedPreferences
import com.mfinatti.wanikanisimple.login.data.UserStorage
import com.mfinatti.wanikanisimple.user.UserDao
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
}
