package com.kkh.multimodule.feature.test

import com.kkh.multimodule.Reducer
import com.kkh.multimodule.SideEffect
import com.kkh.multimodule.UiEvent
import com.kkh.multimodule.UiState

data class TestState(
    val loadingState: String
) : UiState {
    companion object {
        fun init() = TestState(
                loadingState = "init"
        )
    }
}

sealed class TestEvent : UiEvent {
    data object ClickedButton : TestEvent()
    data object OnTestScreenEntered : TestEvent()
}

sealed class TestSideEffect : SideEffect {
    data object NavigateToHome : TestSideEffect()
}

class TestReducer(state: TestState) : Reducer<TestState, TestEvent, SideEffect>(state) {
    override suspend fun reduce(oldState: TestState, event: TestEvent) {

    }
}
