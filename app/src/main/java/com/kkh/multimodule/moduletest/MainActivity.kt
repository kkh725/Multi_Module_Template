package com.kkh.multimodule.moduletest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.ui.NavDisplay
import com.kkh.authEntry
import com.kkh.multimodule.effect.CommonEffect
import com.kkh.multimodule.effect.EffectHelper
import com.kkh.multimodule.moduletest.navigation.TestApp
import com.kkh.multimodule.moduletest.ui.theme.TestModuleTheme
import com.kkh.multimodule.navigaiton.AuthGraph
import com.kkh.multimodule.navigaiton.AuthGraphBaseRoute
import com.kkh.multimodule.navigaiton.NavigationEvent
import com.kkh.multimodule.navigaiton.NavigationHelper
import com.kkh.multimodule.ui.TestBottomSheetScaffoldState
import com.kkh.multimodule.ui.rememberTestBottomSheetScaffoldState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var effectHelper: EffectHelper

    @Inject
    lateinit var navigationHelper: NavigationHelper

    private val viewModel: MainViewModel by viewModels()

    private var lastUpPressTime: Long = 0L

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
                processNavigationEvent(navHostController)
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

    private suspend fun processNavigationEvent(navController: NavHostController) {
        navigationHelper.navigationFlow.collect { event ->

            when (event) {
                is NavigationEvent.To -> {
                    navController.navigate(event.route) {
                        if (event.popUpTo) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = false
                            }
                        }
                    }
                }

                NavigationEvent.Up -> {
                    val hasBackStack = navController.previousBackStackEntry != null

                    if (hasBackStack) {
                        navController.popBackStack()
                    } else {
                        val currentTime = System.currentTimeMillis()

                        if (currentTime - lastUpPressTime < 3000) {
                            this@MainActivity.finishAffinity()
                        } else {
                            lastUpPressTime = currentTime
                            effectHelper.sendEffect(CommonEffect.ShowSnackBar("한 번 더 누르면 종료됩니다."))
                        }
                    }
                }

                is NavigationEvent.TopLevelTo -> {
                    navController.navigate(event.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = true
                        }
                        launchSingleTop = true
                    }
                }

                is NavigationEvent.BottomBarTo -> {
                    navController.navigate(event.route) {
                        popUpTo(navController.graph.id) {
                            inclusive = false
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            }
        }
    }
}
