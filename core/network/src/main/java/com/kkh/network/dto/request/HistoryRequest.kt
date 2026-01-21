package com.kkh.network.dto.request

data class HistoryRequest(
    val userId: String,
    val startDate: String,
    val endDate: String
)