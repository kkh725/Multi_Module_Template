package com.kkh.feature.test.contract

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Immutable
import com.kkh.common.base.UiEvent
import com.kkh.common.base.UiState
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

class TestContract {
    @Immutable
    data class TestState(
        val isLoading: Boolean = false,
        val list: ImmutableList<String> = persistentListOf(),
    ) : UiState

    sealed class TestEvent : UiEvent {
        data object OnButtonClick : TestEvent()

        data class OnNewMatchingCardClick(val content: @Composable () -> Unit) : TestEvent()
    }
}
