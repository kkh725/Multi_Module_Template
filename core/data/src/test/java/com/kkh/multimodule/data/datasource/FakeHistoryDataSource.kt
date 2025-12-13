package com.kkh.multimodule.data.datasource

import com.kkh.multimodule.network.datasource.HistoryDataSource
import com.kkh.multimodule.network.dto.response.HistoryResponse

class FakeHistoryDataSource : HistoryDataSource {

    override suspend fun getTimerHistories(
        userId: String,
        startDate: String,
        endDate: String
    ): HistoryResponse =
        HistoryResponse.mock.copy(userId = userId, startTime = startDate, endTime = endDate)
}