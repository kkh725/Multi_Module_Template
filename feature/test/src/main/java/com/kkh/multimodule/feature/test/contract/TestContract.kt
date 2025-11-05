package com.kkh.multimodule.feature.test.contract

import androidx.compose.runtime.Immutable
import com.kkh.multimodule.base.UiEvent
import com.kkh.multimodule.base.UiState

class TestContract {
    @Immutable
    data class TestState(
        val isLoading : Boolean = false
    ) : UiState

    sealed class TestEvent : UiEvent {
        data object OnButtonClick : TestEvent()
        data class OnNewMatchingCardClick(val cardId : Int) : TestEvent()
    }
}