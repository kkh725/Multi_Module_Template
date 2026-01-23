package com.kkh.network.api

import com.kkh.network.dto.request.HistoryRequest
import com.kkh.network.dto.response.ApiResponse
import com.kkh.network.dto.response.HistoryResponse
import retrofit2.http.Body
import retrofit2.http.GET

interface HistoryApi {
    @GET("/api/timer-histories/me")
    suspend fun getTimerHistories(
        @Body historyRequest: HistoryRequest,
    ): ApiResponse<HistoryResponse>
}
