package com.kkh.multimodule.network.di

import com.kkh.multimodule.network.datasource.HistoryDataSource
import com.kkh.multimodule.network.datasource.HistoryDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DataSourceModule {

    @Binds
    @Singleton
    abstract fun bindHistoryDataSource(
        impl: HistoryDataSourceImpl
    ): HistoryDataSource
}