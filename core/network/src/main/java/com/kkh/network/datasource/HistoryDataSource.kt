package com.kkh.network.datasource

import com.kkh.network.dto.response.HistoryResponse

interface HistoryDataSource {
    suspend fun getTimerHistories(
        userId: String,
        startDate: String,
        endDate: String
    ): HistoryResponse
}