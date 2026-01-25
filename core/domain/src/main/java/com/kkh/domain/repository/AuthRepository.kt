package com.kkh.domain.repository

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val autoLogin: Flow<Boolean>

    suspend fun login(): Result<Unit>

    suspend fun logout(): Result<Unit>
}