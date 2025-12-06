package com.kkh.multimodule.ui

import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import kotlinx.coroutines.launch

@Composable
fun SnackbarHostState.show(message: String) {
    val scope = rememberCoroutineScope()
    scope.launch {
        currentSnackbarData?.dismiss()   // 기존 스낵바 즉시 닫기
        showSnackbar(message)
    }
}
