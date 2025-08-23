package com.kkh.multimodule.network.datasource

import com.kkh.multimodule.network.api.HistoryApi
import com.kkh.multimodule.network.dto.request.HistoryRequest
import com.kkh.multimodule.network.dto.response.ApiResponse
import com.kkh.multimodule.network.dto.response.HistoryResponse
import jakarta.inject.Inject
import retrofit2.Response

class HistoryDataSourceImpl @Inject constructor(private val historyApi: HistoryApi) :
    HistoryDataSource {
    override suspend fun getTimerHistories(
        userId: String,
        startDate: String,
        endDate: String
    ): Response<ApiResponse<HistoryResponse>> {
        val request = HistoryRequest(userId, startDate, endDate)
        return historyApi.getTimerHistories(request)
    }
}