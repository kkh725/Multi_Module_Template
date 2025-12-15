package com.kkh.multimodule.network.interceptor

import okhttp3.Interceptor
import okhttp3.Response
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import okhttp3.Request

class AuthInterceptor @Inject constructor(
    private val tokenManager: TokenManager
) : Interceptor {
    private val oauthUrl = "/oauth/token"

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val requestBuilder = originRequest.newBuilder()

        if (shouldAttachToken(originRequest)) {
            requestBuilder.addHeader(
                "Authorization",
                "Bearer ${runBlocking { tokenManager.getAccessToken() }}"
            )
        }

        return chain.proceed(requestBuilder.build())
    }

    private fun shouldAttachToken(request: Request): Boolean =
        !request.url.encodedPath.contains(oauthUrl)
}
