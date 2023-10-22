package com.hpr.composemap.di

import android.content.Context
import com.hpr.core.db.AppRoom
import com.hpr.core.network.AppInterceptor
import com.hpr.core.network.AppMoshi
import com.hpr.core.network.AppOkHttpClient
import com.hpr.core.network.AppRetrofit
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideContext(baseApp: com.hpr.composemap.base.BaseApp) = baseApp.applicationContext

    @Provides
    @Singleton
    fun provideInterceptor() = AppInterceptor

    @Provides
    @Singleton
    fun provideOkHttpClient(
        appInterceptor: AppInterceptor,
    ) = AppOkHttpClient.build(appInterceptor)

    @Provides
    @Singleton
    fun provideMoshi() = AppMoshi.build()

    @Singleton
    @Provides
    fun provideRetrofit(moshi: Moshi, okHttpClient: OkHttpClient) =
        AppRetrofit.build(moshi, okHttpClient)

    @Singleton
    @Provides
    fun provideDatabase(context: Context, ) = AppRoom.build(context)

}