package com.kkh.multimodule.datastore.di

import com.kkh.multimodule.datastore.datasource.token.LocalTokenDataSource
import com.kkh.multimodule.datastore.datasource.token.LocalTokenDataSourceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    @Singleton
    abstract fun bindLocalTokenDataSource(impl: LocalTokenDataSourceImpl): LocalTokenDataSource
}