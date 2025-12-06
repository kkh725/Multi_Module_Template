package com.kkh.multimodule.moduletest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.SheetState
import androidx.compose.material3.SheetValue
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.kkh.multimodule.effect.CommonEffect
import com.kkh.multimodule.effect.EffectHelper
import com.kkh.multimodule.moduletest.navigation.TestApp
import com.kkh.multimodule.moduletest.ui.theme.TestModuleTheme
import com.kkh.multimodule.ui.TestBottomSheetScaffoldState
import com.kkh.multimodule.ui.rememberTestBottomSheetScaffoldState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var effectHelper: EffectHelper

    private val viewModel: MainViewModel by viewModels()

    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            viewModel.initConfig()
        }

        setContent {
            val navHostController = rememberNavController()
            val sheetState = rememberTestBottomSheetScaffoldState()

            LaunchedEffect(viewModel) {
                processSideEffect(sheetState)
            }

            TestModuleTheme {
                TestApp(
                    navHostController = navHostController,
                    bottomSheetState = sheetState
                )
            }
        }
    }

    @OptIn(ExperimentalMaterial3Api::class)
    private suspend fun processSideEffect(bottomSheetState: TestBottomSheetScaffoldState) {
        effectHelper.effectFlow.collect { event ->
            when (event) {
                is CommonEffect.ShowSnackBar ->
                    bottomSheetState.sheetState.snackbarHostState.showSnackbar(event.text)

                CommonEffect.HideSnackBar ->
                    bottomSheetState.sheetState.snackbarHostState.currentSnackbarData?.dismiss()

                is CommonEffect.ShowBottomSheet -> {
                    bottomSheetState.updateContent(event.content)
                    bottomSheetState.sheetState.bottomSheetState.expand()
                }

                CommonEffect.HideBottomSheet -> bottomSheetState.sheetState.bottomSheetState.hide()
            }
        }
    }
}
