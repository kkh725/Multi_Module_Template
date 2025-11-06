package com.kkh.multimodule.feature.test.contract

import androidx.compose.runtime.Immutable
import com.kkh.multimodule.base.UiEvent
import com.kkh.multimodule.base.UiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

class TestContract {
    @Immutable
    data class TestState(
        val isLoading : Boolean = false,
        val list : ImmutableList<String> = persistentListOf()
    ) : UiState

    sealed class TestEvent : UiEvent {
        data object OnButtonClick : TestEvent()
        data class OnNewMatchingCardClick(val cardId : Int) : TestEvent()
    }
}