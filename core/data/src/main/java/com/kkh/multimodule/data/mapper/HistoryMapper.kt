package com.kkh.multimodule.data.mapper

import com.kkh.multimodule.domain.model.HistoryModel
import com.kkh.multimodule.network.dto.response.HistoryResponse


fun HistoryResponse.toDomain(): HistoryModel {
    return HistoryModel(
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


