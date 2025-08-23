package com.kkh.multimodule.data.repository

import android.util.Log
import com.kkh.multimodule.data.mapper.toDomain
import com.kkh.multimodule.datastore.datasource.LocalDataSource
import com.kkh.multimodule.domain.model.HistoryModel
import com.kkh.multimodule.domain.repository.HistoryRepository
import com.kkh.multimodule.network.datasource.HistoryDataSource
import com.kkh.multimodule.network.dto.response.processApiResponse
import jakarta.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val historyDataSource: HistoryDataSource
) : HistoryRepository {

    override suspend fun localDoit() {
        localDataSource.getCustomText()
    }

    override suspend fun getTimerHistories(
        userId: String,
        startDate: String,
        endDate: String
    ): Result<HistoryModel> =
        runCatching {
            historyDataSource.getTimerHistories(userId, startDate, endDate).processApiResponse()
                .toDomain()
        }
}