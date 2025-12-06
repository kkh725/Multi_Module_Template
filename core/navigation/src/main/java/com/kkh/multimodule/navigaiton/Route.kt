package com.kkh.multimodule.navigaiton

import com.kkh.multimodule.domain.model.HistoryModel
import kotlinx.serialization.Serializable

sealed interface Route

@Serializable
data object AuthGraphBaseRoute : Route

// route -> 그래프 형식으로 묶여야 하는 경우
sealed interface AuthGraph : Route {
    @Serializable
    data class LoginRoute(val history: HistoryModel) : AuthGraph{
        companion object { const val KEY_HISTORY = "history" }
    }

    @Serializable
    data object VerificationRoute : AuthGraph

    @Serializable
    data object SignUpRoute : AuthGraph
}

@Serializable
data class TestRoute(val testId : Int) : Route {
    companion object { const val KEY_TEST_ID = "testId" }
}

@Serializable
data object PauseRoute : Route
