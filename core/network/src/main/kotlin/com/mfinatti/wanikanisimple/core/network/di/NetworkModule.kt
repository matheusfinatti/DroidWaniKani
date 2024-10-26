package com.mfinatti.wanikanisimple.core.network.di

import com.mfinatti.wanikanisimple.core.network.RemoteWKDataSource
import com.mfinatti.wanikanisimple.core.network.adapters.ResponseAdapter
import com.mfinatti.wanikanisimple.core.network.data.model.subject.KanaVocabularyDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.KanjiDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.RadicalDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.SubjectDTO
import com.mfinatti.wanikanisimple.core.network.data.model.subject.VocabularyDTO
import com.mfinatti.wanikanisimple.core.network.retrofit.RetrofitWKService
import com.mfinatti.wanikanisimple.core.network.retrofit.RetrofitWKServiceApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMoshi(): Moshi =
        Moshi.Builder()
            .add(ResponseAdapter(Moshi.Builder().build()))
            .build()

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, moshi: Moshi): Retrofit =
        Retrofit.Builder()
            .baseUrl("https://api.wanikani.com/v2/")
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()

    @Provides
    @Singleton
    fun provideWKDataSource(retrofit: Retrofit): RemoteWKDataSource =
        RetrofitWKService(
            retrofit.create(RetrofitWKServiceApi::class.java)
        )
}
