package com.mfinatti.wanikanisimple.core.network.di

import android.content.Context
import android.content.SharedPreferences
import com.mfinatti.wanikanisimple.core.network.RemoteWKDataSource
import com.mfinatti.wanikanisimple.core.network.adapters.ResponseAdapter
import com.mfinatti.wanikanisimple.core.network.interceptors.ApiKeyInterceptor
import com.mfinatti.wanikanisimple.core.network.interceptors.ApiVersionInterceptor
import com.mfinatti.wanikanisimple.core.network.retrofit.RetrofitWKService
import com.mfinatti.wanikanisimple.core.network.retrofit.RetrofitWKServiceApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.io.File
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        @ApplicationContext context: Context,
        prefs: SharedPreferences
    ): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val apiKeyInterceptor = ApiKeyInterceptor(prefs)
        val apiVersionInterceptor = ApiVersionInterceptor()

        return OkHttpClient.Builder()
            .cache(Cache(
                directory = File(context.cacheDir, "http_cache"),
                maxSize = 50L * 1024L * 1024L // 50MiB
            ))
            .addInterceptor(loggingInterceptor)
            .addInterceptor(apiKeyInterceptor)
            .addInterceptor(apiVersionInterceptor)
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
