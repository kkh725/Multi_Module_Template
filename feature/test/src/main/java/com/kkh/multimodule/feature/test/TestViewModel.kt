package com.kkh.multimodule.feature.test

import androidx.lifecycle.SavedStateHandle
import com.kkh.multimodule.base.BaseViewModel
import com.kkh.multimodule.domain.model.error.ErrorHelper
import com.kkh.multimodule.domain.repository.HistoryRepository
import com.kkh.multimodule.feature.test.contract.TestContract.TestEvent
import com.kkh.multimodule.feature.test.contract.TestContract.TestState
import com.kkh.multimodule.navigaiton.AuthGraphBaseRoute
import com.kkh.multimodule.navigaiton.NavigationEvent.To
import com.kkh.multimodule.navigaiton.NavigationHelper
import com.kkh.multimodule.navigaiton.TestRoute
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val historyRepository: HistoryRepository,
    private val navigationHelper: NavigationHelper,
    private val errorHelper: ErrorHelper,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<TestState, TestEvent>(initialState = TestState()) {
    private val testId: String by lazy { requireNotNull(savedStateHandle[TestRoute.KEY_TEST_ID]) }

    override suspend fun processEvent(event: TestEvent) {
        when (event) {
            is TestEvent.OnButtonClick -> navigationHelper.navigate(To(AuthGraphBaseRoute))
            is TestEvent.OnNewMatchingCardClick -> errorHelper.sendError(Exception("msg"))
        }
    }
}