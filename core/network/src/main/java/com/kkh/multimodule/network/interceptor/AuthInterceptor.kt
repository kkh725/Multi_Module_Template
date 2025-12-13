package com.kkh.multimodule.network.interceptor

import com.kkh.multimodule.TokenProvider
import okhttp3.Interceptor
import okhttp3.Response
import jakarta.inject.Inject
import kotlinx.coroutines.runBlocking
import okhttp3.Request

// network module
class AuthInterceptor @Inject constructor(
    private val tokenProvider: TokenProvider
) : Interceptor {
    private val oauthUrl = "/oauth/token"

    override fun intercept(chain: Interceptor.Chain): Response {
        val original = chain.request()

        val token = runBlocking {
            tokenProvider.getAccessToken()
        }

        val builder = original.newBuilder().method(original.method, original.body)
        if (shouldAttachToken(request = original)) token.let {
            builder.header(
                "Authorization",
                "Bearer $it"
            )
        }

        return chain.proceed(builder.build())
    }

    private fun shouldAttachToken(request: Request): Boolean =
        !request.url.encodedPath.contains(oauthUrl)
}
