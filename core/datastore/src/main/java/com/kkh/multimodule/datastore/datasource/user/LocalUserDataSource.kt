package com.kkh.multimodule.datastore.datasource.user

import kotlinx.coroutines.flow.Flow

interface LocalUserDataSource {
    val autoLogin: Flow<Boolean>

    suspend fun setAutoLogin(autoLogin: Boolean)
}