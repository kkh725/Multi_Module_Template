package com.kkh.multimodule.data.repository

import com.kkh.multimodule.database.dao.UserDao
import com.kkh.multimodule.database.entity.UserEntry
import com.kkh.multimodule.datastore.datasource.LocalDataSource
import com.kkh.multimodule.domain.model.history.HistoryModel
import com.kkh.multimodule.domain.repository.HistoryRepository
import com.kkh.multimodule.network.datasource.HistoryDataSource
import jakarta.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val historyDataSource: HistoryDataSource
) : HistoryRepository {
    override suspend fun localDoit() = localDataSource.getCustomText()
    override suspend fun getTimerHistories(
        userId: String,
        startDate: String,
        endDate: String
    ): Result<HistoryModel> =
        runCatching {
            val res = historyDataSource.getTimerHistories(userId, startDate, endDate).toDomain()

            localDataSource.saveCustomText(res.userId)

            res
        }
}