package com.kkh.multimodule.domain.repository

import com.kkh.multimodule.domain.model.history.HistoryModel

interface HistoryRepository {
    suspend fun localDoit() : String
    suspend fun getTimerHistories(
        userId: String,
        startDate: String,
        endDate: String
    ) : Result<HistoryModel>
}