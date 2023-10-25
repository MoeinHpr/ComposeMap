package com.hpr.data.module

import com.hpr.data.api.MapApi
import com.hpr.data.db.base.AppDatabase
import com.hpr.data.db.MapDao
import com.hpr.data.repository.map.MapRepository
import com.hpr.data.repository.map.MapRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class MapModule {

    @Provides
    fun provideMapApi(retrofit: Retrofit): MapApi =
        retrofit.create(MapApi::class.java)


    @Provides
    fun provideMapDao(appDatabase: AppDatabase): MapDao =
        appDatabase.mapDao()

}