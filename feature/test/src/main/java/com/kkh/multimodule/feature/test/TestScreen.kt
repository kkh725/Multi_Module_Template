package com.kkh.multimodule.feature.test

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.kkh.multimodule.CommonEffect

@Composable
fun TestScreen(
    testViewModel: TestViewModel = hiltViewModel(),
    onClickButtonTonNavigate: () -> Unit
) {
    val navController = rememberNavController()

    val uiState by testViewModel.uiState.collectAsState()
    val snackBarHostState = remember { SnackbarHostState() }
    var showDialog by remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        testViewModel.sendEvent(TestEvent.OnTestScreenEntered)
        testViewModel.sideEffect.collect { effect ->
            when(effect){
                is CommonEffect.ShowSnackBar -> {
                    snackBarHostState.showSnackbar(effect.message)
                }
                is TestSideEffect.NavigateToHome -> onClickButtonTonNavigate
            }
        }
    }

    Scaffold { paddingValues ->
        Column(modifier = Modifier.padding(paddingValues)) {
            Button(onClick = {
                testViewModel.sendEvent(TestEvent.ClickedButton)
            }) {
                Text(
                    "Button!"
                )
            }
        }
    }
}