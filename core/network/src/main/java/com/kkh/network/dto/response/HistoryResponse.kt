package com.kkh.network.dto.response

import com.kkh.domain.model.history.HistoryModel
import kotlinx.serialization.Serializable

@Serializable
data class HistoryResponse(
    val id: Int,
    val timerId: Int,
    val userId: String,
    val title: String,
    val focusTypeId: Int,
    val repeatCycleCode: String,
    val repeatDays: String,
    val historyDt: String,
    val historyStatus: String,
    val failReason: String?,
    val startTime: String,
    val endTime: String,
) {
    companion object {
        val mock =
            HistoryResponse(
                id = 1,
                timerId = 101,
                userId = "user_001",
                title = "Study Session",
                focusTypeId = 2,
                repeatCycleCode = "DAILY",
                repeatDays = "Mon,Tue,Wed",
                historyDt = "2025-12-11",
                historyStatus = "COMPLETED",
                failReason = null,
                startTime = "09:00",
                endTime = "10:00",
            )
    }

    fun toDomain() =
        HistoryModel(
            id = this.id,
            timerId = this.timerId,
            userId = this.userId,
            title = this.title,
            focusTypeId = this.focusTypeId,
            repeatCycleCode = this.repeatCycleCode,
            repeatDays = this.repeatDays,
            historyDt = this.historyDt,
            historyStatus = this.historyStatus,
            failReason = this.failReason,
            startTime = this.startTime,
            endTime = this.endTime,
        )
}