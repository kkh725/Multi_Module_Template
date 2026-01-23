package com.kkh.navigation

import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

sealed interface Route : NavKey

@Serializable
data object AuthGraphBaseRoute : Route

// route -> 그래프 형식으로 묶여야 하는 경우
sealed interface AuthGraph : Route {
    @Serializable
    data class LoginRoute(
        val historyId: Int,
    ) : AuthGraph {
        companion object {
            const val KEY_HISTORY_ID = "historyId"
        }
    }

    @Serializable
    data object VerificationRoute : AuthGraph

    @Serializable
    data object SignUpRoute : AuthGraph
}

@Serializable
data class TestRoute(
    val testId: Int,
) : Route {
    companion object {
        const val KEY_TEST_ID = "testId"
    }
}

@Serializable
data object PauseRoute : Route

@Serializable
data object AuthRoute : Route