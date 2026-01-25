package com.kkh.network.di

import com.kkh.network.datasource.HistoryDataSource
import com.kkh.network.datasource.HistoryDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataSourceModule {
    @Binds
    @Singleton
    fun bindHistoryDataSource(impl: HistoryDataSourceImpl): HistoryDataSource
}