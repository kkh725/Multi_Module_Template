package com.kkh.multimodule.data.repository

import com.kkh.multimodule.datastore.datasource.token.LocalTokenDataSource
import com.kkh.multimodule.network.interceptor.TokenManager
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TokenManagerImpl @Inject constructor(private val localTokenDataSource: LocalTokenDataSource) :
    TokenManager {
    override suspend fun getAccessToken(): String = localTokenDataSource.accessToken.first()
}