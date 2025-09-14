package com.kkh.multimodule.datastore.datasource

import androidx.datastore.preferences.core.stringPreferencesKey
import com.kkh.multimodule.TokenProvider
import com.kkh.multimodule.datastore.DataStoreManager
import kotlinx.coroutines.flow.first
import javax.inject.Inject

class TokenDataSourceImpl @Inject constructor(
    private val dataStoreManager: DataStoreManager
) : TokenProvider, TokenDataSource {
    private val uuidKey = stringPreferencesKey("uuid_key")
    private val accessTokenKey = stringPreferencesKey("accessTokenKey")

    override suspend fun getUuid() : String{
        return dataStoreManager.readString(uuidKey).first()
    }

    override suspend fun saveUuid(uuid : String){
        dataStoreManager.saveString(uuidKey, uuid)
    }

    override suspend fun getAccessToken() : String{
        return dataStoreManager.readString(accessTokenKey).first()
    }

    override suspend fun saveAccessToken(accessToken : String){
        dataStoreManager.saveString(accessTokenKey, accessToken)
    }
}