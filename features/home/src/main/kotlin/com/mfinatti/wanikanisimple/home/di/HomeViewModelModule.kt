package com.mfinatti.wanikanisimple.home.di

import com.mfinatti.wanikanisimple.home.data.SummaryRepositoryImpl
import com.mfinatti.wanikanisimple.home.domain.SummaryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeViewModelModule {

    @Binds
    abstract fun bindSummaryRepository(
        summaryRepositoryImpl: SummaryRepositoryImpl,
    ): SummaryRepository
}
