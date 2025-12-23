package com.kkh.multimodule.feature.test

import androidx.lifecycle.SavedStateHandle
import com.kkh.multimodule.base.BaseViewModel
import com.kkh.multimodule.designsystem.R
import com.kkh.multimodule.domain.model.error.ErrorHelper
import com.kkh.multimodule.domain.repository.AuthRepository
import com.kkh.multimodule.effect.CommonEffect.ShowBottomSheet
import com.kkh.multimodule.effect.CommonEffect.ShowSnackBar
import com.kkh.multimodule.effect.EffectHelper
import com.kkh.multimodule.effect.SnackBarSideEffect
import com.kkh.multimodule.feature.test.contract.TestContract.TestEvent
import com.kkh.multimodule.feature.test.contract.TestContract.TestState
import com.kkh.multimodule.navigation.NavigationHelper
import com.kkh.multimodule.navigation.TestRoute
import com.kkh.multimodule.ui.SnackBarState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject

@HiltViewModel
class TestViewModel @Inject constructor(
    private val authRepository: AuthRepository,
    private val navigationHelper: NavigationHelper,
    internal val effectHelper: EffectHelper,
    private val errorHelper: ErrorHelper,
    private val savedStateHandle: SavedStateHandle
) : BaseViewModel<TestState, TestEvent>(initialState = TestState()) {
    private val testId: String by lazy { requireNotNull(savedStateHandle[TestRoute.KEY_TEST_ID]) }

    override suspend fun processEvent(event: TestEvent) {
        when (event) {
            is TestEvent.OnButtonClick ->
                postEffect(SnackBarSideEffect.Info(R.string.core_designsystem_snackbar_match_accepted))
            is TestEvent.OnNewMatchingCardClick -> effectHelper.sendEffect(
                ShowBottomSheet(event.content)
            )
        }
    }
}