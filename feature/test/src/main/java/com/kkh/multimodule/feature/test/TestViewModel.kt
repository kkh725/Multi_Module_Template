package com.kkh.multimodule.feature.test

import com.kkh.multimodule.BaseViewModel
import com.kkh.multimodule.CommonEffect
import com.kkh.multimodule.SideEffect
import com.kkh.multimodule.domain.repository.HistoryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(private val historyRepository: HistoryRepository) :
    BaseViewModel<TestState, TestEvent, SideEffect>(reducer = TestReducer(TestState.init())) {

    override suspend fun processEvent(event: TestEvent) {
        super.processEvent(event)
        when (event) {
            is TestEvent.ClickedButton -> {
                getTimerHistories("userId", "startDate", "endDate")
            }

            else -> {}
        }
    }

    private suspend fun getTimerHistories(userId: String, startDate: String, endDate: String) {
        historyRepository.getTimerHistories(userId, startDate, endDate)
            .onSuccess {
                sendEffect(CommonEffect.ShowDialog(true))
                sendEffect(TestSideEffect.NavigateToHome)
            }
            .onFailure { throwable ->
                sendEffect(CommonEffect.ShowSnackBar("${throwable.message}"))
                sendEffect(TestSideEffect.NavigateToHome)
            }
    }
}