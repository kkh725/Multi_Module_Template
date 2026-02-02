package com.kkh.feature.test

import androidx.compose.foundation.background
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
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.flowWithLifecycle
import com.kkh.common.effect.CommonEffect.ShowSnackBar
import com.kkh.common.effect.SnackBarSideEffect
import com.kkh.common.effect.getMessage
import com.kkh.common.effect.handle
import com.kkh.common.ui.SnackBarState.Info
import com.kkh.feature.test.contract.TestContract
import com.skydoves.compose.stability.runtime.TraceRecomposition

@Suppress("NonSkippableComposable")
@TraceRecomposition
@Composable
fun TestRoute(
    modifier: Modifier = Modifier,
    viewModel: TestViewModel = hiltViewModel(),
) {
    val uiState by viewModel.state.collectAsStateWithLifecycle()
    val lifecycle = LocalLifecycleOwner.current.lifecycle
    val context = LocalContext.current

    LaunchedEffect(viewModel) {
        viewModel.sideEffect.flowWithLifecycle(lifecycle).collect { effect ->
            when {
                effect is SnackBarSideEffect -> {
                    effect.handle { _, _ ->
                        val message = effect.getMessage(context)

                        viewModel.effectHelper.sendEffect(ShowSnackBar(Info(message)))
                    }
                }
            }
        }
    }

    viewModel.sendEvent(
        TestContract.TestEvent.OnNewMatchingCardClick(
            content = {
                Column(
                    Modifier
                        .fillMaxWidth()
                        .background(Color.Blue),
                ) {
                    Button(onClick = { viewModel.sendEvent(TestContract.TestEvent.OnButtonClick) }) {
                        Text("bt")
                    }
                    Spacer(Modifier.height(100.dp))
                }
            }
        ),
    )
}