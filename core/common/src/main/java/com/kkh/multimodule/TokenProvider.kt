package com.kkh.multimodule

interface TokenProvider {
    suspend fun getAccessToken(): String
    suspend fun getUuid(): String
}