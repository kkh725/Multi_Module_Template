package com.kkh.multimodule.datastore.datasource.user

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.kkh.multimodule.datastore.util.getValue
import com.kkh.multimodule.datastore.util.setValue
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import javax.inject.Named

class LocalUserDataSourceImpl @Inject constructor(
    @param:Named("user") private val dataStore: DataStore<Preferences>
) : LocalUserDataSource {
    override val autoLogin: Flow<Boolean> = dataStore.getValue(AUTO_LOGIN, false)

    override suspend fun setAutoLogin(autoLogin: Boolean) { dataStore.setValue(AUTO_LOGIN, autoLogin) }

    companion object { private val AUTO_LOGIN = booleanPreferencesKey("AUTO_LOGIN") }
}
