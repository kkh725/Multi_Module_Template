package com.kkh.multimodule.navigaiton

import kotlinx.serialization.Serializable

sealed interface Route

@Serializable
data object AuthGraphBaseRoute : Route

// route -> 그래프 형식으로 묶여야 하는 경우
sealed interface AuthGraph : Route {
    @Serializable
    data object LoginRoute : AuthGraph

    @Serializable
    data object VerificationRoute : AuthGraph

    @Serializable
    data object SignUpRoute : AuthGraph
}

// 단일 route
@Serializable
data object TestRoute : Route

@Serializable
data object PauseRoute : Route
