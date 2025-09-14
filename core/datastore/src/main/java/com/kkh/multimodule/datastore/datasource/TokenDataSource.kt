package com.kkh.multimodule.datastore.datasource

interface TokenDataSource {
    suspend fun saveUuid(uuid : String)
    suspend fun saveAccessToken(accessToken : String)
}