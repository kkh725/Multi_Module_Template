package com.kkh.network.datasource

import com.kkh.network.api.HistoryApi
import com.kkh.network.dto.request.HistoryRequest
import com.kkh.network.dto.response.HistoryResponse
import com.kkh.network.dto.response.processApiResponse
import jakarta.inject.Inject

class HistoryDataSourceImpl
    @Inject
    constructor(private val historyApi: HistoryApi) :
    HistoryDataSource {
        override suspend fun getTimerHistories(
            userId: String,
            startDate: String,
            endDate: String,
        ): HistoryResponse {
            val request = HistoryRequest(userId, startDate, endDate)
            return historyApi.getTimerHistories(request).processApiResponse()
        }
    }