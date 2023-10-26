package com.hpr.composemap.di

import android.content.Context
import com.hpr.composemap.base.BaseApp
import com.hpr.core.db.AppRoom
import com.hpr.core.network.AppInterceptor
import com.hpr.core.network.AppMoshi
import com.hpr.core.network.AppOkHttpClient
import com.hpr.core.network.AppRetrofit
import com.hpr.core.utils.AppCoil
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.internal.managers.ApplicationComponentManager
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AppModule {

    @Provides
    fun provideContext(baseApp: BaseApp) : Context = baseApp.applicationContext

    @Provides
    fun provideInterceptor() = AppInterceptor

    @Provides
    fun provideOkHttpClient(
        appInterceptor: AppInterceptor,
    ) = AppOkHttpClient.build(appInterceptor)

    @Provides
    fun provideMoshi() = AppMoshi.build()

    @Provides
    fun provideMoshiConverterFactory(moshi: Moshi) = AppMoshi.moshiConverter(moshi)

    @Provides
    fun provideRetrofit(moshiConverterFactory : MoshiConverterFactory, okHttpClient: OkHttpClient) =
        AppRetrofit.build(moshiConverterFactory, okHttpClient)

    @Provides
    fun provideDatabase(@ApplicationContext context: Context) = AppRoom.build(context)

    @Provides
    fun provideCoil(@ApplicationContext context: Context) = AppCoil.build(context)

}