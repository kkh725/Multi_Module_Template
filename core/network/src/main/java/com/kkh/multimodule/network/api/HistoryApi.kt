package com.kkh.multimodule.network.api

import com.kkh.multimodule.network.dto.request.HistoryRequest
import com.kkh.multimodule.network.dto.response.ApiResponse
import com.kkh.multimodule.network.dto.response.HistoryResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET


interface HistoryApi {
    @GET("/api/timer-histories/me")
    suspend fun getTimerHistories(@Body historyRequest: HistoryRequest): ApiResponse<HistoryResponse>
}


