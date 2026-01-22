package com.kkh.data.datasource

import com.kkh.network.datasource.HistoryDataSource
import com.kkh.network.dto.response.HistoryResponse

class FakeHistoryDataSource : HistoryDataSource {

    override suspend fun getTimerHistories(
        userId: String,
        startDate: String,
        endDate: String
    ): HistoryResponse =
        HistoryResponse.mock.copy(userId = userId, startTime = startDate, endTime = endDate)
}