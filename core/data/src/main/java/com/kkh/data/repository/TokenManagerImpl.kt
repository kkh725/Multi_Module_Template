package com.kkh.data.repository

import com.kkh.datastore.datasource.token.LocalTokenDataSource
import com.kkh.network.interceptor.TokenManager
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TokenManagerImpl
    @Inject
    constructor(
        private val localTokenDataSource: LocalTokenDataSource,
    ) : TokenManager {
        override suspend fun getAccessToken(): String = localTokenDataSource.accessToken.first()
    }