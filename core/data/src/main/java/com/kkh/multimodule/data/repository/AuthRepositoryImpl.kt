package com.kkh.multimodule.data.repository

import com.kkh.multimodule.datastore.datasource.token.LocalTokenDataSource
import com.kkh.multimodule.datastore.datasource.user.LocalUserDataSource
import com.kkh.multimodule.domain.repository.AuthRepository
import com.kkh.multimodule.suspendRunCatching
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl @Inject constructor(
    private val localUserDataSource: LocalUserDataSource,
    private val localTokenDataSource: LocalTokenDataSource
) : AuthRepository {
    override val autoLogin: Flow<Boolean> = localUserDataSource.autoLogin

    override suspend fun login(): Result<Unit> = suspendRunCatching {
        // dataStore single-writer. no parallel
        localTokenDataSource.setAccessToken("accessToken")
        localTokenDataSource.setRefreshToken("refreshToken")
        localUserDataSource.setAutoLogin(true)
    }

    override suspend fun logout(): Result<Unit> = suspendRunCatching {
        localTokenDataSource.clearToken()
        localUserDataSource.setAutoLogin(false)
    }
}