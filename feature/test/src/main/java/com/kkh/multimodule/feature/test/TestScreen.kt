package com.kkh.multimodule.feature.test

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TestRoute(
    testViewModel: TestViewModel = hiltViewModel()
) {
    val uiState by testViewModel.state.collectAsStateWithLifecycle()
}