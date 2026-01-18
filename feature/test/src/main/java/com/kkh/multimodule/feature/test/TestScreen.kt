package com.kkh.multimodule.feature.test

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kkh.multimodule.effect.CommonEffect.ShowSnackBar
import com.kkh.multimodule.effect.SnackBarSideEffect
import com.kkh.multimodule.effect.getMessage
import com.kkh.multimodule.effect.handle
import com.kkh.multimodule.feature.test.contract.TestContract
import com.kkh.multimodule.feature.test.contract.TestContract.TestState
import com.kkh.multimodule.ui.SnackBarState.Info
import com.skydoves.compose.stability.runtime.TraceRecomposition
import kotlinx.collections.immutable.ImmutableList

@Suppress("NonSkippableComposable")
@TraceRecomposition
@Composable
fun TestRoute(
    viewModel: TestViewModel = hiltViewModel(),
    onClick: () -> Unit
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.collect { effect ->
            when {
                effect is SnackBarSideEffect -> effect.handle { _, _ ->
                    val message = effect.getMessage(context)

                    viewModel.effectHelper.sendEffect(ShowSnackBar(Info(message)))
                }
            }
        }
    }

    viewModel.sendEvent(TestContract.TestEvent.OnNewMatchingCardClick(@Composable {
        Column(
            Modifier
                .fillMaxWidth()
                .background(Color.Blue)
        ) {
            Button(onClick = { viewModel.sendEvent(TestContract.TestEvent.OnButtonClick) }) {
                Text("bt")
            }
            Spacer(Modifier.height(100.dp))

        }

    }))
}

@Composable
fun Test(
    a: StableClass
) {
    Text("ddd")
}

data class StableClass(
    val list: ImmutableList<String>,
    var str: String
)