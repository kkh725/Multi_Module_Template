package com.kkh.network.interceptor

interface TokenManager {
    suspend fun getAccessToken(): String
}