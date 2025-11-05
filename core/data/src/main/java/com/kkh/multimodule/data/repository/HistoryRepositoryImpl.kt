package com.kkh.multimodule.data.repository

import com.kkh.multimodule.data.mapper.toDomain
import com.kkh.multimodule.database.dao.UserDao
import com.kkh.multimodule.database.entity.UserEntry
import com.kkh.multimodule.datastore.datasource.LocalDataSource
import com.kkh.multimodule.domain.model.HistoryModel
import com.kkh.multimodule.domain.repository.HistoryRepository
import com.kkh.multimodule.network.datasource.HistoryDataSource
import jakarta.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val userDao: UserDao,
    private val localDataSource: LocalDataSource,
    private val historyDataSource: HistoryDataSource
) : HistoryRepository {
    private suspend fun getUserId(): UserEntry? = userDao.getById(123123)
    override suspend fun localDoit() = localDataSource.getCustomText()
    override suspend fun getTimerHistories(
        userId: String,
        startDate: String,
        endDate: String
    ): Result<HistoryModel> =
        runCatching { historyDataSource.getTimerHistories(userId, startDate, endDate).toDomain() }
}