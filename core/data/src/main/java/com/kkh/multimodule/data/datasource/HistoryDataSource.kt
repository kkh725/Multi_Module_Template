package com.kkh.multimodule.data.datasource

import com.kkh.multimodule.network.dto.response.HistoryResponse

interface HistoryDataSource {
    suspend fun getTimerHistories(
        userId: String,
        startDate: String,
        endDate: String
    ): HistoryResponse
}