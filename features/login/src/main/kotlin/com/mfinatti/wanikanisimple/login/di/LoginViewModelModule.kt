package com.mfinatti.wanikanisimple.login.di

import com.mfinatti.wanikanisimple.login.data.UserManagerImpl
import com.mfinatti.wanikanisimple.login.domain.UserManager
import com.mfinatti.wanikanisimple.subject.data.SubjectRepositoryImpl
import com.mfinatti.wanikanisimple.subject.domain.SubjectRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class LoginViewModelModule {

    @Binds
    abstract fun bindUserRepository(
        userRepositoryImpl: UserManagerImpl,
    ): UserManager

    @Binds
    abstract fun bindSubjectRepository(
        subjectRepositoryImpl: SubjectRepositoryImpl,
    ): SubjectRepository
}
