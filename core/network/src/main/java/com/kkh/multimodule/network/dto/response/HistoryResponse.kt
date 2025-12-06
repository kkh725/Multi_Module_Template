package com.kkh.multimodule.network.dto.response

import com.kkh.multimodule.domain.model.history.HistoryModel
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
    val endTime: String
) {
    fun toDomain() = HistoryModel(
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
        endTime = this.endTime
    )
}