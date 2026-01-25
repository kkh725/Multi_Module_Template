package com.kkh.data.datasource

import com.kkh.datastore.datasource.token.LocalTokenDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeLocalTokenDataSource : LocalTokenDataSource {
    private val _accessToken = MutableStateFlow("")
    override val accessToken: Flow<String> = _accessToken

    private val _refreshToken = MutableStateFlow("")
    override val refreshToken: Flow<String> = _refreshToken

    override suspend fun setAccessToken(accessToken: String) {
        _accessToken.value = accessToken
    }

    override suspend fun setRefreshToken(refreshToken: String) {
        _refreshToken.value = refreshToken
    }

    override suspend fun clearToken() {
        _accessToken.value = ""
        _refreshToken.value = ""
    }
}