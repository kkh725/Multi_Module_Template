package com.kkh.data.datasource

import com.kkh.datastore.datasource.user.LocalUserDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class FakeLocalUserDataSource : LocalUserDataSource {
    private val _autoLogin = MutableStateFlow(false)
    override val autoLogin: Flow<Boolean> = _autoLogin

    override suspend fun setAutoLogin(autoLogin: Boolean) {
        _autoLogin.value = autoLogin
    }
}
