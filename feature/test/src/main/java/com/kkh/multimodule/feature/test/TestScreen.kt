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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kkh.multimodule.feature.test.contract.TestContract
import com.kkh.multimodule.feature.test.contract.TestContract.TestState
import com.skydoves.compose.stability.runtime.TraceRecomposition
import kotlinx.collections.immutable.ImmutableList

@Suppress("NonSkippableComposable")
@TraceRecomposition
@Composable
fun TestRoute(
    testViewModel: TestViewModel = hiltViewModel(),
    onClick: () -> Unit
) {
    val uiState by testViewModel.state.collectAsStateWithLifecycle()

    testViewModel.sendEvent(TestContract.TestEvent.OnNewMatchingCardClick(@Composable {
        Column (
            Modifier
                .fillMaxWidth()
                .background(Color.Blue)
        ) {
            Button(onClick = {testViewModel.sendEvent(TestContract.TestEvent.OnButtonClick)}) {
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