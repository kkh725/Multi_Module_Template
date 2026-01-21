package com.kkh.common.ui

import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember

@OptIn(ExperimentalMaterial3Api::class)
class TestBottomSheetScaffoldState(
    initialContent: @Composable () -> Unit = {},
    val sheetState: BottomSheetScaffoldState
) {
    private val _contentState = mutableStateOf<(@Composable () -> Unit)>(initialContent)
    val content: @Composable () -> Unit get() = _contentState.value

    fun updateContent(newContent: @Composable () -> Unit) {
        _contentState.value = newContent
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun rememberTestBottomSheetScaffoldState(): TestBottomSheetScaffoldState {
    val sheetState = rememberStandardBottomSheetState(skipHiddenState = false)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)

    return remember(scaffoldState) {
        TestBottomSheetScaffoldState(
            sheetState = scaffoldState
        )
    }
}
