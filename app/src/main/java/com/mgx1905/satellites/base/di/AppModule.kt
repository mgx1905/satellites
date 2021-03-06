package com.mgx1905.satellites.base.di

import com.mgx1905.satellites.ui.detail.repository.SatelliteDetailRepository
import com.mgx1905.satellites.ui.detail.repository.SatelliteDetailRepositoryImpl
import com.mgx1905.satellites.ui.list.repository.SatellitesListRepository
import com.mgx1905.satellites.ui.list.repository.SatellitesListRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by mgx1905 on 26.12.2021
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideSatellitesListRepository(): SatellitesListRepository {
        return SatellitesListRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideSatelliteDetailRepository(): SatelliteDetailRepository {
        return SatelliteDetailRepositoryImpl()
    }
}