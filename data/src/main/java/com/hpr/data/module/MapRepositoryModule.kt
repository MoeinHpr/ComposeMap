package com.hpr.data.module

import com.hpr.data.repository.map.MapRepository
import com.hpr.data.repository.map.MapRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
interface MapRepositoryModule {

    @Binds
    fun provideRepository(mapRepository: MapRepositoryImpl) : MapRepository
}