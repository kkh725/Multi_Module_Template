package com.kkh.data.repository

import com.kkh.common.suspendRunCatching
import com.kkh.datastore.datasource.token.LocalTokenDataSource
import com.kkh.datastore.datasource.user.LocalUserDataSource
import com.kkh.domain.repository.AuthRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl
    @Inject
    constructor(
        private val localUserDataSource: LocalUserDataSource,
        private val localTokenDataSource: LocalTokenDataSource,
    ) : AuthRepository {
        override val autoLogin: Flow<Boolean> = localUserDataSource.autoLogin

        override suspend fun login(): Result<Unit> =
            suspendRunCatching {
                // dataStore single-writer. no parallel
                localTokenDataSource.setAccessToken("accessToken")
                localTokenDataSource.setRefreshToken("refreshToken")
                localUserDataSource.setAutoLogin(true)
            }

        override suspend fun logout(): Result<Unit> =
            suspendRunCatching {
                localTokenDataSource.clearToken()
                localUserDataSource.setAutoLogin(false)
            }
    }
