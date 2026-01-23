package com.kkh.common.effect

import androidx.compose.runtime.Composable
import com.kkh.common.ui.SnackBarState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.BUFFERED
import kotlinx.coroutines.flow.receiveAsFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EffectHelper
    @Inject
    constructor() {
        private val _effectFlow = Channel<CommonEffect>(BUFFERED)
        val effectFlow = _effectFlow.receiveAsFlow()

        fun sendEffect(effect: CommonEffect) {
            _effectFlow.trySend(effect)
        }
    }

sealed class CommonEffect {
    data class ShowSnackBar(val snackBarState: SnackBarState) : CommonEffect()

    data object HideSnackBar : CommonEffect()

    data class ShowBottomSheet(val content: @Composable () -> Unit) : CommonEffect()

    data object HideBottomSheet : CommonEffect()
}
