package com.kkh.multimodule.network.datasource

import com.kkh.multimodule.network.dto.response.ApiResponse
import com.kkh.multimodule.network.dto.response.HistoryResponse
import retrofit2.Response

interface HistoryDataSource {
    suspend fun getTimerHistories(
        userId: String,
        startDate: String,
        endDate: String
    ): Response<ApiResponse<HistoryResponse>>
}