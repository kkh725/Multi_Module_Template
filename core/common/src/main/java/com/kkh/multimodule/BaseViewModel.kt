package com.kkh.multimodule

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : UiState, Event : UiEvent, Effect : SideEffect>(
    protected val reducer: Reducer<State, Event, Effect>
) : ViewModel() {

    val uiState get() = reducer.uiState
    val sideEffect get() = reducer.effect

    fun sendEvent(event: Event) {
        viewModelScope.launch {
            reducer.sendEvent(event)
            processEvent(event)
        }
    }

    fun sendEffect(effect: Effect) {
        viewModelScope.launch {
            reducer.sendEffect(effect)
        }
    }

    open suspend fun processEvent(event: Event) {}
}