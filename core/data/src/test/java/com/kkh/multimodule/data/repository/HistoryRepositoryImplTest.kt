package com.kkh.multimodule.data.repository

import com.kkh.multimodule.data.datasource.FakeHistoryDataSource
import com.kkh.multimodule.data.datasource.FakeLocalDataSource
import com.kkh.multimodule.datastore.datasource.LocalDataSource
import com.kkh.multimodule.network.datasource.HistoryDataSource
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class HistoryRepositoryImplTest {
    private lateinit var localDataSource: LocalDataSource
    private lateinit var historyDataSource: HistoryDataSource
    private lateinit var historyRepository: HistoryRepositoryImpl

    @BeforeEach
    fun setUp() {
        localDataSource = FakeLocalDataSource()
        historyDataSource = FakeHistoryDataSource()
        historyRepository = HistoryRepositoryImpl(localDataSource, historyDataSource)
    }

    @Test
    fun `localDoit 실행 시 localDataSource의 getCustomText()가 호출되는지 확인한다`() = runTest {
        // given
        localDataSource.saveCustomText("Hello Test")

        // when
        val result = historyRepository.localDoit()

        // then
        assertEquals("Hello Test", result)
    }

    @Test
    fun `save_response`() = runTest {
        // given
        val userId = "userId"
        val startDate = "2025.12.12"
        val endDate = "2025.12.30"

        // when
        historyRepository.getTimerHistories(userId, startDate, endDate)

        // then
        assertTrue(localDataSource.getCustomText().contains("userId"))
    }
}