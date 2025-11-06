package com.kkh.multimodule.feature.test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.kkh.multimodule.feature.test.contract.TestContract.TestState
import com.skydoves.compose.stability.runtime.TraceRecomposition
import kotlinx.collections.immutable.ImmutableList

@Suppress("NonSkippableComposable")
@TraceRecomposition
@Composable
fun TestRoute(
    testViewModel: TestViewModel = hiltViewModel(),
    onClick : () -> Unit
) {
    val uiState by testViewModel.state.collectAsStateWithLifecycle()
}

@Composable
fun Test(
    uiState : TestState,
    list : ImmutableList<String>,
){

}