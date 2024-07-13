package com.mfinatti.wanikanisimple.user.di

import com.mfinatti.wanikanisimple.user.data.UserRepositoryImpl
import com.mfinatti.wanikanisimple.user.domain.UserRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class UserViewModelModule {

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserRepositoryImpl,
    ): UserRepository
}