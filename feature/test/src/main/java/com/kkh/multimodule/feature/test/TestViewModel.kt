package com.kkh.multimodule.feature.test

import com.kkh.multimodule.base.BaseViewModel
import com.kkh.multimodule.domain.repository.HistoryRepository
import com.kkh.multimodule.feature.test.contract.TestContract.TestEvent
import com.kkh.multimodule.feature.test.contract.TestContract.TestState
import com.kkh.multimodule.navigaiton.AuthGraphBaseRoute
import com.kkh.multimodule.navigaiton.NavigationEvent.To
import com.kkh.multimodule.navigaiton.NavigationHelper

import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val historyRepository: HistoryRepository,
    private val navigationHelper: NavigationHelper
) : BaseViewModel<TestState, TestEvent>(initialState = TestState()) {

    override suspend fun processEvent(event: TestEvent) {
        when (event) {
            is TestEvent.OnButtonClick -> navigationHelper.navigate(To(AuthGraphBaseRoute))
            is TestEvent.OnNewMatchingCardClick -> {}
        }
    }
}