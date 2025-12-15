package com.kkh.multimodule.network.interceptor

interface TokenManager {
    suspend fun getAccessToken(): String
}